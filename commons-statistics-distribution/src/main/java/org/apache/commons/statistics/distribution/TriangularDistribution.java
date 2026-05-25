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

/**
 * Implementation of the triangular distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; a, b, c) = \begin{cases}
 *       \frac{2(x-a)}{(b-a)(c-a)} &amp; \text{for } a \le x \lt c \\
 *       \frac{2}{b-a}             &amp; \text{for } x = c \\
 *       \frac{2(b-x)}{(b-a)(b-c)} &amp; \text{for } c \lt x \le b \\
 *       \end{cases} \]
 *
 * <p>for \( -\infty \lt a \le c \le b \lt \infty \) and
 * \( x \in [a, b] \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Triangular_distribution">Triangular distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/TriangularDistribution.html">Triangular distribution (MathWorld)</a>
 */
public final class TriangularDistribution extends AbstractContinuousDistribution {

    /**
     * Lower limit of this distribution (inclusive).
     */
    private final double a;

    /**
     * Upper limit of this distribution (inclusive).
     */
    private final double b;

    /**
     * Mode of this distribution.
     */
    private final double c;

    /**
     * Cached value ((b - a) * (c - a).
     */
    private final double divisor1;

    /**
     * Cached value ((b - a) * (b - c)).
     */
    private final double divisor2;

    /**
     * Cumulative probability at the mode.
     */
    private final double cdfMode;

    /**
     * Survival probability at the mode.
     */
    private final double sfMode;

    /**
     * @param a Lower limit of this distribution (inclusive).
     * @param c Mode of this distribution.
     * @param b Upper limit of this distribution (inclusive).
     */
    private TriangularDistribution(double a, double c, double b) {
        this.a = a;
        this.c = c;
        this.b = b;
        divisor1 = (b - a) * (c - a);
        divisor2 = (b - a) * (b - c);
        cdfMode = (c - a) / (b - a);
        sfMode = (b - c) / (b - a);
    }

    /**
     * Creates a triangular distribution.
     *
     * @param a Lower limit of this distribution (inclusive).
     * @param c Mode of this distribution.
     * @param b Upper limit of this distribution (inclusive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code a >= b}, if {@code c > b} or if
     * {@code c < a}.
     */
    public static TriangularDistribution of(double a, double c, double b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the mode parameter of this distribution.
     *
     * @return the mode.
     */
    public double getMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double density(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double cumulativeProbability(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double survivalProbability(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseSurvivalProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower limit \( a \), upper limit \( b \), and mode \( c \),
     * the mean is \( (a + b + c) / 3 \).
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower limit \( a \), upper limit \( b \), and mode \( c \),
     * the variance is \( (a^2 + b^2 + c^2 - ab - ac - bc) / 18 \).
     */
    @Override
    public double getVariance() {
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
     * {@code b} of the distribution.
     */
    @Override
    public double getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
