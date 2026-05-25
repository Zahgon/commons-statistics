/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.statistics.distribution;

import org.apache.commons.rng.UniformRandomProvider;

/**
 * Implementation of the trapezoidal distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; a, b, c, d) = \begin{cases}
 *       \frac{2}{d+c-a-b}\frac{x-a}{b-a} &amp; \text{for } a\le x \lt b \\
 *       \frac{2}{d+c-a-b}                &amp; \text{for } b\le x \lt c \\
 *       \frac{2}{d+c-a-b}\frac{d-x}{d-c} &amp; \text{for } c\le x \le d
 *       \end{cases} \]
 *
 * <p>for \( -\infty \lt a \le b \le c \le d \lt \infty \) and
 * \( x \in [a, d] \).
 *
 * <p>Note the special cases:
 * <ul>
 * <li>\( b = c \) is the triangular distribution</li>
 * <li>\( a = b \) and \( c = d \) is the uniform distribution</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Trapezoidal_distribution">Trapezoidal distribution (Wikipedia)</a>
 */
public abstract class TrapezoidalDistribution extends AbstractContinuousDistribution {

    /**
     * Lower limit of this distribution (inclusive).
     */
    protected final double a;

    /**
     * Start of the trapezoid constant density.
     */
    protected final double b;

    /**
     * End of the trapezoid constant density.
     */
    protected final double c;

    /**
     * Upper limit of this distribution (inclusive).
     */
    protected final double d;

    /**
     * Specialisation of the trapezoidal distribution used when the distribution simplifies
     * to an alternative distribution.
     */
    private static class DelegatedTrapezoidalDistribution extends TrapezoidalDistribution {

        /**
         * Distribution delegate.
         */
        private final ContinuousDistribution delegate;

        /**
         * @param a Lower limit of this distribution (inclusive).
         * @param b Start of the trapezoid constant density.
         * @param c End of the trapezoid constant density.
         * @param d Upper limit of this distribution (inclusive).
         * @param delegate Distribution delegate.
         */
        DelegatedTrapezoidalDistribution(double a, double b, double c, double d, ContinuousDistribution delegate) {
            super(a, b, c, d);
            this.delegate = delegate;
        }

        @Override
        public double density(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double probability(double x0, double x1) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double logDensity(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double cumulativeProbability(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double inverseCumulativeProbability(double p) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double survivalProbability(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double inverseSurvivalProbability(double p) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getMean() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getVariance() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Sampler createSampler(UniformRandomProvider rng) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Specialisation of the trapezoidal distribution used when {@code b == c}.
     *
     * <p>This delegates all methods to the triangular distribution.
     */
    private static class TriangularTrapezoidalDistribution extends DelegatedTrapezoidalDistribution {

        /**
         * @param a Lower limit of this distribution (inclusive).
         * @param b Start/end of the trapezoid constant density (mode).
         * @param d Upper limit of this distribution (inclusive).
         */
        TriangularTrapezoidalDistribution(double a, double b, double d) {
            super(a, b, b, d, TriangularDistribution.of(a, b, d));
        }
    }

    /**
     * Specialisation of the trapezoidal distribution used when {@code a == b} and {@code c == d}.
     *
     * <p>This delegates all methods to the uniform distribution.
     */
    private static class UniformTrapezoidalDistribution extends DelegatedTrapezoidalDistribution {

        /**
         * @param a Lower limit of this distribution (inclusive).
         * @param d Upper limit of this distribution (inclusive).
         */
        UniformTrapezoidalDistribution(double a, double d) {
            super(a, a, d, d, UniformContinuousDistribution.of(a, d));
        }
    }

    /**
     * Regular implementation of the trapezoidal distribution.
     */
    private static class RegularTrapezoidalDistribution extends TrapezoidalDistribution {

        /**
         * Cached value (d + c - a - b).
         */
        private final double divisor;

        /**
         * Cached value (b - a).
         */
        private final double bma;

        /**
         * Cached value (d - c).
         */
        private final double dmc;

        /**
         * Cumulative probability at b.
         */
        private final double cdfB;

        /**
         * Cumulative probability at c.
         */
        private final double cdfC;

        /**
         * Survival probability at b.
         */
        private final double sfB;

        /**
         * Survival probability at c.
         */
        private final double sfC;

        /**
         * @param a Lower limit of this distribution (inclusive).
         * @param b Start of the trapezoid constant density.
         * @param c End of the trapezoid constant density.
         * @param d Upper limit of this distribution (inclusive).
         */
        RegularTrapezoidalDistribution(double a, double b, double c, double d) {
            super(a, b, c, d);
            // Sum positive terms
            divisor = (d - a) + (c - b);
            bma = b - a;
            dmc = d - c;
            cdfB = bma / divisor;
            sfB = 1 - cdfB;
            sfC = dmc / divisor;
            cdfC = 1 - sfC;
        }

        @Override
        public double density(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double cumulativeProbability(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double survivalProbability(double x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double inverseCumulativeProbability(double p) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double inverseSurvivalProbability(double p) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getMean() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getVariance() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the {@code k}-th non-central moment of the standardized trapezoidal
         * distribution.
         *
         * <p>Shifting the distribution by scale {@code (d - a)} and location {@code a}
         * creates a standardized trapezoidal distribution. This has a simplified
         * non-central moment as {@code a = 0, d = 1, 0 <= b < c <= 1}.
         * <pre>
         *               2             1       ( 1 - c^(k+2)           )
         * E[X^k] = ----------- -------------- ( ----------- - b^(k+1) )
         *          (1 + c - b) (k + 1)(k + 2) (    1 - c              )
         * </pre>
         *
         * <p>Simplification eliminates issues computing the moments when {@code a == b}
         * or {@code c == d} in the original (non-standardized) distribution.
         *
         * @param k Moment to compute
         * @param b Start of the trapezoid constant density (shape parameter in [0, 1]).
         * @param c End of the trapezoid constant density (shape parameter in [0, 1]).
         * @return the moment
         */
        private static double nonCentralMoment(int k, double b, double c) {
            // As c -> 1 then (1 - c^(k+2)) loses precision
            // 1 - x^y == -(x^y - 1)    [high precision powm1]
            //         == -(exp(y * log(x)) - 1)
            // Note: avoid log(1) using the limit:
            // (1 - c^(k+2)) / (1-c) -> (k+2) as c -> 1
            final double term1 = c == 1 ? k + 2 : Math.expm1((k + 2) * Math.log(c)) / (c - 1);
            final double term2 = Math.pow(b, k + 1);
            return 2 * ((term1 - term2) / (c - b + 1) / ((k + 1) * (k + 2)));
        }
    }

    /**
     * @param a Lower limit of this distribution (inclusive).
     * @param b Start of the trapezoid constant density.
     * @param c End of the trapezoid constant density.
     * @param d Upper limit of this distribution (inclusive).
     */
    TrapezoidalDistribution(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Creates a trapezoidal distribution.
     *
     * <p>The distribution density is represented as an up sloping line from
     * {@code a} to {@code b}, constant from {@code b} to {@code c}, and then a down
     * sloping line from {@code c} to {@code d}.
     *
     * @param a Lower limit of this distribution (inclusive).
     * @param b Start of the trapezoid constant density (first shape parameter).
     * @param c End of the trapezoid constant density (second shape parameter).
     * @param d Upper limit of this distribution (inclusive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code a >= d}, if {@code b < a}, if
     * {@code c < b} or if {@code c > d}.
     */
    public static TrapezoidalDistribution of(double a, double b, double c, double d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower limit \( a \), start of the density constant region \( b \),
     * end of the density constant region \( c \) and upper limit \( d \), the
     * mean is:
     *
     * <p>\[ \frac{1}{3(d+c-b-a)}\left(\frac{d^3-c^3}{d-c}-\frac{b^3-a^3}{b-a}\right) \]
     */
    @Override
    public abstract double getMean();

    /**
     * {@inheritDoc}
     *
     * <p>For lower limit \( a \), start of the density constant region \( b \),
     * end of the density constant region \( c \) and upper limit \( d \), the
     * variance is:
     *
     * <p>\[ \frac{1}{6(d+c-b-a)}\left(\frac{d^4-c^4}{d-c}-\frac{b^4-a^4}{b-a}\right) - \mu^2 \]
     *
     * <p>where \( \mu \) is the mean.
     */
    @Override
    public abstract double getVariance();

    /**
     * Gets the start of the constant region of the density function.
     *
     * <p>This is the first shape parameter {@code b} of the distribution.
     *
     * @return the first shape parameter {@code b}
     */
    public double getB() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the end of the constant region of the density function.
     *
     * <p>This is the second shape parameter {@code c} of the distribution.
     *
     * @return the second shape parameter {@code c}
     */
    public double getC() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is equal to the lower limit parameter
     * {@code a} of the distribution.
     */
    @Override
    public double getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is equal to the upper limit parameter
     * {@code d} of the distribution.
     */
    @Override
    public double getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
