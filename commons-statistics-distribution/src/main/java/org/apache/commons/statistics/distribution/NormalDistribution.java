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

import org.apache.commons.numbers.gamma.ErfDifference;
import org.apache.commons.numbers.gamma.Erfc;
import org.apache.commons.numbers.gamma.InverseErfc;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.GaussianSampler;
import org.apache.commons.rng.sampling.distribution.ZigguratSampler;

/**
 * Implementation of the normal (Gaussian) distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \mu, \sigma) = \frac 1 {\sigma\sqrt{2\pi}} e^{-{\frac 1 2}\left( \frac{x-\mu}{\sigma} \right)^2 } \]
 *
 * <p>for \( \mu \) the mean,
 * \( \sigma &gt; 0 \) the standard deviation, and
 * \( x \in (-\infty, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Normal_distribution">Normal distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/NormalDistribution.html">Normal distribution (MathWorld)</a>
 */
public final class NormalDistribution extends AbstractContinuousDistribution {

    /**
     * Mean of this distribution.
     */
    private final double mean;

    /**
     * Standard deviation of this distribution.
     */
    private final double standardDeviation;

    /**
     * The value of {@code log(sd) + 0.5*log(2*pi)} stored for faster computation.
     */
    private final double logStandardDeviationPlusHalfLog2Pi;

    /**
     * Standard deviation multiplied by sqrt(2).
     * This is used to avoid a double division when computing the value passed to the
     * error function:
     * <pre>
     *  ((x - u) / sd) / sqrt(2) == (x - u) / (sd * sqrt(2)).
     *  </pre>
     * <p>Note: Implementations may first normalise x and then divide by sqrt(2) resulting
     * in differences due to rounding error that show increasingly large relative
     * differences as the error function computes close to 0 in the extreme tail.
     */
    private final double sdSqrt2;

    /**
     * Standard deviation multiplied by sqrt(2 pi). Computed to high precision.
     */
    private final double sdSqrt2pi;

    /**
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     */
    private NormalDistribution(double mean, double sd) {
        this.mean = mean;
        standardDeviation = sd;
        logStandardDeviationPlusHalfLog2Pi = Math.log(sd) + Constants.HALF_LOG_TWO_PI;
        // Minimise rounding error by computing sqrt(2 * sd * sd) exactly.
        // Compute using extended precision with care to avoid over/underflow.
        sdSqrt2 = ExtendedPrecision.sqrt2xx(sd);
        // Compute sd * sqrt(2 * pi)
        sdSqrt2pi = ExtendedPrecision.xsqrt2pi(sd);
    }

    /**
     * Creates a normal distribution.
     *
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @return the distribution
     * @throws IllegalArgumentException if {@code sd <= 0}.
     */
    public static NormalDistribution of(double mean, double sd) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the standard deviation parameter of this distribution.
     *
     * @return the standard deviation.
     */
    public double getStandardDeviation() {
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
    public double probability(double x0, double x1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logDensity(double x) {
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
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For standard deviation parameter \( \sigma \), the variance is \( \sigma^2 \).
     */
    @Override
    public double getVariance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public ContinuousDistribution.Sampler createSampler(final UniformRandomProvider rng) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
