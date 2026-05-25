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
import org.apache.commons.rng.sampling.distribution.LogNormalSampler;
import org.apache.commons.rng.sampling.distribution.ZigguratSampler;

/**
 * Implementation of the log-normal distribution.
 *
 * <p>\( X \) is log-normally distributed if its natural logarithm \( \ln(x) \)
 * is normally distributed. The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \mu, \sigma) = \frac 1 {x\sigma\sqrt{2\pi\,}} e^{-{\frac 1 2}\left( \frac{\ln x-\mu}{\sigma} \right)^2 } \]
 *
 * <p>for \( \mu \) the mean of the normally distributed natural logarithm of this distribution,
 * \( \sigma &gt; 0 \) the standard deviation of the normally distributed natural logarithm of this
 * distribution, and
 * \( x \in (0, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Log-normal_distribution">Log-normal distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/LogNormalDistribution.html">Log-normal distribution (MathWorld)</a>
 */
public final class LogNormalDistribution extends AbstractContinuousDistribution {

    /**
     * &radic;(2 &pi;).
     */
    private static final double SQRT2PI = Math.sqrt(2 * Math.PI);

    /**
     * The mu parameter of this distribution.
     */
    private final double mu;

    /**
     * The sigma parameter of this distribution.
     */
    private final double sigma;

    /**
     * The value of {@code log(sigma) + 0.5 * log(2*PI)} stored for faster computation.
     */
    private final double logSigmaPlusHalfLog2Pi;

    /**
     * Sigma multiplied by sqrt(2).
     */
    private final double sigmaSqrt2;

    /**
     * Sigma multiplied by sqrt(2 * pi).
     */
    private final double sigmaSqrt2Pi;

    /**
     * @param mu Mean of the natural logarithm of the distribution values.
     * @param sigma Standard deviation of the natural logarithm of the distribution values.
     */
    private LogNormalDistribution(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
        logSigmaPlusHalfLog2Pi = Math.log(sigma) + Constants.HALF_LOG_TWO_PI;
        sigmaSqrt2 = ExtendedPrecision.sqrt2xx(sigma);
        sigmaSqrt2Pi = sigma * SQRT2PI;
    }

    /**
     * Creates a log-normal distribution.
     *
     * @param mu Mean of the natural logarithm of the distribution values.
     * @param sigma Standard deviation of the natural logarithm of the distribution values.
     * @return the distribution
     * @throws IllegalArgumentException if {@code sigma <= 0}.
     */
    public static LogNormalDistribution of(double mu, double sigma) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the {@code mu} parameter of this distribution.
     * This is the mean of the natural logarithm of the distribution values,
     * not the mean of distribution.
     *
     * @return the mu parameter.
     */
    public double getMu() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the {@code sigma} parameter of this distribution.
     * This is the standard deviation of the natural logarithm of the distribution values,
     * not the standard deviation of distribution.
     *
     * @return the sigma parameter.
     */
    public double getSigma() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For {@code mu}, and sigma {@code s} of this distribution, the PDF
     * is given by
     * <ul>
     * <li>{@code 0} if {@code x <= 0},</li>
     * <li>{@code exp(-0.5 * ((ln(x) - mu) / s)^2) / (s * sqrt(2 * pi) * x)}
     * otherwise.</li>
     * </ul>
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
     *
     * <p>See documentation of {@link #density(double)} for computation details.
     */
    @Override
    public double logDensity(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For {@code mu}, and sigma {@code s} of this distribution, the CDF
     * is given by
     * <ul>
     * <li>{@code 0} if {@code x <= 0},</li>
     * <li>{@code 0} if {@code ln(x) - mu < 0} and {@code mu - ln(x) > 40 * s}, as
     * in these cases the actual value is within {@link Double#MIN_VALUE} of 0,</li>
     * <li>{@code 1} if {@code ln(x) - mu >= 0} and {@code ln(x) - mu > 40 * s},
     * as in these cases the actual value is within {@link Double#MIN_VALUE} of
     * 1,</li>
     * <li>{@code 0.5 + 0.5 * erf((ln(x) - mu) / (s * sqrt(2))} otherwise.</li>
     * </ul>
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
     * <p>For \( \mu \) the mean of the normally distributed natural logarithm of
     * this distribution, \( \sigma &gt; 0 \) the standard deviation of the normally
     * distributed natural logarithm of this distribution, the mean is:
     *
     * <p>\[ \exp(\mu + \frac{\sigma^2}{2}) \]
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For \( \mu \) the mean of the normally distributed natural logarithm of
     * this distribution, \( \sigma &gt; 0 \) the standard deviation of the normally
     * distributed natural logarithm of this distribution, the variance is:
     *
     * <p>\[ [\exp(\sigma^2) - 1)] \exp(2 \mu + \sigma^2) \]
     */
    @Override
    public double getVariance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is always 0.
     *
     * @return 0.
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
