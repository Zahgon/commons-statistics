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
 * Implementation of the Gumbel distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \mu, \beta) =  \frac{1}{\beta} e^{-(z+e^{-z})} \]
 *
 * <p>where \[ z = \frac{x - \mu}{\beta} \]
 *
 * <p>for \( \mu \) the location,
 * \( \beta &gt; 0 \) the scale, and
 * \( x \in (-\infty, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Gumbel_distribution">Gumbel distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/GumbelDistribution.html">Gumbel distribution (MathWorld)</a>
 */
public final class GumbelDistribution extends AbstractContinuousDistribution {

    /**
     * Support lower bound.
     */
    private static final double SUPPORT_LO = Double.NEGATIVE_INFINITY;

    /**
     * Support upper bound.
     */
    private static final double SUPPORT_HI = Double.POSITIVE_INFINITY;

    /**
     * &pi;<sup>2</sup>/6. https://oeis.org/A013661.
     */
    private static final double PI_SQUARED_OVER_SIX = 1.644934066848226436472415166646;

    /**
     * <a href="https://en.wikipedia.org/wiki/Euler%27s_constant">
     * Approximation of Euler's constant</a>.
     * https://oeis.org/A001620.
     */
    private static final double EULER = 0.5772156649015328606065;

    /**
     * ln(ln(2)). https://oeis.org/A074785.
     */
    private static final double LN_LN_2 = -0.3665129205816643270124;

    /**
     * Location parameter.
     */
    private final double mu;

    /**
     * Scale parameter.
     */
    private final double beta;

    /**
     * @param mu Location parameter.
     * @param beta Scale parameter (must be positive).
     */
    private GumbelDistribution(double mu, double beta) {
        this.beta = beta;
        this.mu = mu;
    }

    /**
     * Creates a Gumbel distribution.
     *
     * @param mu Location parameter.
     * @param beta Scale parameter (must be positive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code beta <= 0}
     */
    public static GumbelDistribution of(double mu, double beta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the location parameter of this distribution.
     *
     * @return the location parameter.
     */
    public double getLocation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the scale parameter of this distribution.
     *
     * @return the scale parameter.
     */
    public double getScale() {
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
     *
     * <p>For location parameter \( \mu \) and scale parameter \( \beta \), the mean is:
     *
     * <p>\[ \mu + \beta \gamma \]
     *
     * <p>where \( \gamma \) is the
     * <a href="https://mathworld.wolfram.com/Euler-MascheroniConstantApproximations.html">
     * Euler-Mascheroni constant</a>.
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For scale parameter \( \beta \), the variance is:
     *
     * <p>\[ \frac{\pi^2}{6} \beta^2 \]
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
    double getMedian() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
