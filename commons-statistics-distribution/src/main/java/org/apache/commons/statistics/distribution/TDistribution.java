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

import org.apache.commons.numbers.gamma.Beta;
import org.apache.commons.numbers.gamma.LogBeta;
import org.apache.commons.numbers.gamma.RegularizedBeta;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.TSampler;

/**
 * Implementation of Student's t-distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; v) = \frac{\Gamma(\frac{\nu+1}{2})} {\sqrt{\nu\pi}\,\Gamma(\frac{\nu}{2})} \left(1+\frac{t^2}{\nu} \right)^{\!-\frac{\nu+1}{2}} \]
 *
 * <p>for \( v &gt; 0 \) the degrees of freedom,
 * \( \Gamma \) is the gamma function, and
 * \( x \in (-\infty, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Student%27s_t-distribution">Student&#39;s t-distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/Studentst-Distribution.html">Student&#39;s t-distribution (MathWorld)</a>
 */
public abstract class TDistribution extends AbstractContinuousDistribution {

    /**
     * The degrees of freedom.
     */
    private final double degreesOfFreedom;

    /**
     * Specialisation of the T-distribution used when there are infinite degrees of freedom.
     * In this case the distribution matches a normal distribution. This is used when the
     * variance is not different from 1.0.
     *
     * <p>This delegates all methods to the standard normal distribution. Instances are
     * allowed to provide access to the degrees of freedom used during construction.
     */
    private static class NormalTDistribution extends TDistribution {

        /**
         * A standard normal distribution used for calculations.
         * This is immutable and thread-safe and can be used across instances.
         */
        private static final NormalDistribution STANDARD_NORMAL = NormalDistribution.of(0, 1);

        /**
         * @param degreesOfFreedom Degrees of freedom.
         */
        NormalTDistribution(double degreesOfFreedom) {
            super(degreesOfFreedom);
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

        // Survival probability functions inherit the symmetry operations from the TDistribution
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
     * Implementation of Student's T-distribution.
     */
    private static class StudentsTDistribution extends TDistribution {

        /**
         * 2.
         */
        private static final double TWO = 2;

        /**
         * The threshold for the density function where the
         * power function base minus 1 is close to zero.
         */
        private static final double CLOSE_TO_ZERO = 0.25;

        /**
         * -(v + 1) / 2, where v = degrees of freedom.
         */
        private final double mvp1Over2;

        /**
         * Density normalisation factor, sqrt(v) * beta(1/2, v/2), where v = degrees of freedom.
         */
        private final double densityNormalisation;

        /**
         * Log density normalisation term, 0.5 * log(v) + log(beta(1/2, v/2)), where v = degrees of freedom.
         */
        private final double logDensityNormalisation;

        /**
         * Cached value for inverse probability function.
         */
        private final double mean;

        /**
         * Cached value for inverse probability function.
         */
        private final double variance;

        /**
         * @param degreesOfFreedom Degrees of freedom.
         * @param variance Precomputed variance
         */
        StudentsTDistribution(double degreesOfFreedom, double variance) {
            super(degreesOfFreedom);
            mvp1Over2 = -0.5 * (degreesOfFreedom + 1);
            densityNormalisation = Math.sqrt(degreesOfFreedom) * Beta.value(0.5, degreesOfFreedom / 2);
            logDensityNormalisation = 0.5 * Math.log(degreesOfFreedom) + LogBeta.value(0.5, degreesOfFreedom / 2);
            mean = degreesOfFreedom > 1 ? 0 : Double.NaN;
            this.variance = variance;
        }

        /**
         * @param degreesOfFreedom Degrees of freedom.
         * @return the variance
         */
        static double computeVariance(double degreesOfFreedom) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double density(double x) {
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
        public double getMean() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getVariance() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        double getMedian() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Sampler createSampler(UniformRandomProvider rng) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * @param degreesOfFreedom Degrees of freedom.
     */
    TDistribution(double degreesOfFreedom) {
        this.degreesOfFreedom = degreesOfFreedom;
    }

    /**
     * Creates a Student's t-distribution.
     *
     * @param degreesOfFreedom Degrees of freedom.
     * @return the distribution
     * @throws IllegalArgumentException if {@code degreesOfFreedom <= 0}
     */
    public static TDistribution of(double degreesOfFreedom) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the degrees of freedom parameter of this distribution.
     *
     * @return the degrees of freedom.
     */
    public double getDegreesOfFreedom() {
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
    public double inverseSurvivalProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For degrees of freedom parameter \( v \), the mean is:
     *
     * <p>\[ \mathbb{E}[X] = \begin{cases}
     *       0                &amp; \text{for } v \gt 1 \\
     *       \text{undefined} &amp; \text{otherwise}
     *       \end{cases} \]
     *
     * @return the mean, or {@link Double#NaN NaN} if it is not defined.
     */
    @Override
    public abstract double getMean();

    /**
     * {@inheritDoc}
     *
     * <p>For degrees of freedom parameter \( v \), the variance is:
     *
     * <p>\[ \operatorname{var}[X] = \begin{cases}
     *       \frac{v}{v - 2}  &amp; \text{for } v \gt 2 \\
     *       \infty           &amp; \text{for } 1 \lt v \le 2 \\
     *       \text{undefined} &amp; \text{otherwise}
     *       \end{cases} \]
     *
     * @return the variance, or {@link Double#NaN NaN} if it is not defined.
     */
    @Override
    public abstract double getVariance();

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is always negative infinity.
     *
     * @return {@linkplain Double#NEGATIVE_INFINITY negative infinity}.
     */
    @Override
    public double getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is always positive infinity.
     *
     * @return {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     */
    @Override
    public double getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
