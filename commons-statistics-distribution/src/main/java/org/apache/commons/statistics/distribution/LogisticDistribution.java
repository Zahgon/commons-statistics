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
 * Implementation of the logistic distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \mu, s) = \frac{e^{-(x-\mu)/s}} {s\left(1+e^{-(x-\mu)/s}\right)^2} \]
 *
 * <p>for \( \mu \) the location,
 * \( s &gt; 0 \) the scale, and
 * \( x \in (-\infty, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Logistic_distribution">Logistic distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/LogisticDistribution.html">Logistic distribution (MathWorld)</a>
 */
public final class LogisticDistribution extends AbstractContinuousDistribution {

    /**
     * Support lower bound.
     */
    private static final double SUPPORT_LO = Double.NEGATIVE_INFINITY;

    /**
     * Support upper bound.
     */
    private static final double SUPPORT_HI = Double.POSITIVE_INFINITY;

    /**
     * &pi;<sup>2</sup>/3. https://oeis.org/A195055.
     */
    private static final double PI_SQUARED_OVER_THREE = 3.289868133696452872944830;

    /**
     * Location parameter.
     */
    private final double mu;

    /**
     * Scale parameter.
     */
    private final double scale;

    /**
     * Logarithm of "scale".
     */
    private final double logScale;

    /**
     * @param mu Location parameter.
     * @param scale Scale parameter (must be positive).
     */
    private LogisticDistribution(double mu, double scale) {
        this.mu = mu;
        this.scale = scale;
        this.logScale = Math.log(scale);
    }

    /**
     * Creates a logistic distribution.
     *
     * @param mu Location parameter.
     * @param scale Scale parameter (must be positive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code scale <= 0}.
     */
    public static LogisticDistribution of(double mu, double scale) {
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
     * <p>The mean is equal to the {@linkplain #getLocation() location}.
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For scale parameter \( s \), the variance is:
     *
     * <p>\[ \frac{s^2 \pi^2}{3} \]
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
