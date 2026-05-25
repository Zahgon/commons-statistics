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

import org.apache.commons.numbers.gamma.Erf;
import org.apache.commons.numbers.gamma.Erfc;
import org.apache.commons.numbers.gamma.InverseErf;
import org.apache.commons.numbers.gamma.InverseErfc;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.LevySampler;

/**
 * Implementation of the L&eacute;vy distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \mu, c) = \sqrt{\frac{c}{2\pi}}~~\frac{e^{ -\frac{c}{2(x-\mu)}}} {(x-\mu)^{3/2}} \]
 *
 * <p>for \( \mu \) the location,
 * \( c &gt; 0 \) the scale, and
 * \( x \in [\mu, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/L%C3%A9vy_distribution">L&eacute;vy distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/LevyDistribution.html">L&eacute;vy distribution (MathWorld)</a>
 */
public final class LevyDistribution extends AbstractContinuousDistribution {

    /**
     * 1 / 2(erfc^-1 (0.5))^2. Computed using Matlab's VPA to 30 digits.
     */
    private static final double HALF_OVER_ERFCINV_HALF_SQUARED = 2.1981093383177324039996779530797;

    /**
     * Location parameter.
     */
    private final double mu;

    /**
     * Scale parameter.
     */
    private final double c;

    /**
     * Half of c (for calculations).
     */
    private final double halfC;

    /**
     * @param mu Location parameter.
     * @param c Scale parameter.
     */
    private LevyDistribution(double mu, double c) {
        this.mu = mu;
        this.c = c;
        this.halfC = 0.5 * c;
    }

    /**
     * Creates a Levy distribution.
     *
     * @param mu Location parameter.
     * @param c Scale parameter.
     * @return the distribution
     * @throws IllegalArgumentException if {@code c <= 0}.
     */
    public static LevyDistribution of(double mu, double c) {
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
     *
     * <p>If {@code x} is less than the location parameter then {@code 0} is
     * returned, as in these cases the distribution is not defined.
     */
    @Override
    public double density(final double x) {
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
    public double cumulativeProbability(final double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double survivalProbability(final double x) {
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
     * <p>The mean is equal to positive infinity.
     *
     * @return {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The variance is equal to positive infinity.
     *
     * @return {@linkplain Double#POSITIVE_INFINITY positive infinity}.
     */
    @Override
    public double getVariance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is the {@linkplain #getLocation() location}.
     *
     * @return location.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ContinuousDistribution.Sampler createSampler(final UniformRandomProvider rng) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
