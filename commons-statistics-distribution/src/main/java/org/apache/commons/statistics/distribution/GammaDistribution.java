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

import org.apache.commons.numbers.gamma.LogGamma;
import org.apache.commons.numbers.gamma.RegularizedGamma;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.AhrensDieterMarsagliaTsangGammaSampler;

/**
 * Implementation of the gamma distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x;k,\theta) = \frac{x^{k-1}e^{-x/\theta}}{\theta^k\Gamma(k)} \]
 *
 * <p>for \( k &gt; 0 \) the shape, \( \theta &gt; 0 \) the scale, \( \Gamma(k) \) is the gamma function
 * and \( x \in (0, \infty) \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Gamma_distribution">Gamma distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/GammaDistribution.html">Gamma distribution (MathWorld)</a>
 */
public final class GammaDistribution extends AbstractContinuousDistribution {

    /**
     * Support lower bound.
     */
    private static final double SUPPORT_LO = 0;

    /**
     * Support upper bound.
     */
    private static final double SUPPORT_HI = Double.POSITIVE_INFINITY;

    /**
     * The shape parameter.
     */
    private final double shape;

    /**
     * The scale parameter.
     */
    private final double scale;

    /**
     * Precomputed term for the log density: {@code -log(gamma(shape)) - log(scale)}.
     */
    private final double minusLogGammaShapeMinusLogScale;

    /**
     * Cached value for inverse probability function.
     */
    private final double mean;

    /**
     * Cached value for inverse probability function.
     */
    private final double variance;

    /**
     * @param shape Shape parameter.
     * @param scale Scale parameter.
     */
    private GammaDistribution(double shape, double scale) {
        this.shape = shape;
        this.scale = scale;
        this.minusLogGammaShapeMinusLogScale = -LogGamma.value(shape) - Math.log(scale);
        mean = shape * scale;
        variance = shape * scale * scale;
    }

    /**
     * Creates a gamma distribution.
     *
     * @param shape Shape parameter.
     * @param scale Scale parameter.
     * @return the distribution
     * @throws IllegalArgumentException if {@code shape <= 0} or {@code scale <= 0}.
     */
    public static GammaDistribution of(double shape, double scale) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the shape parameter of this distribution.
     *
     * @return the shape parameter.
     */
    public double getShape() {
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
     * <p>Returns the limit when {@code x = 0}:
     * <ul>
     * <li>{@code shape < 1}: Infinity</li>
     * <li>{@code shape == 1}: 1 / scale</li>
     * <li>{@code shape > 1}: 0</li>
     * </ul>
     */
    @Override
    public double density(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Returns the limit when {@code x = 0}:
     * <ul>
     * <li>{@code shape < 1}: Infinity</li>
     * <li>{@code shape == 1}: -log(scale)</li>
     * <li>{@code shape > 1}: -Infinity</li>
     * </ul>
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
     *
     * <p>For shape parameter \( k \) and scale parameter \( \theta \), the
     * mean is \( k \theta \).
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For shape parameter \( k \) and scale parameter \( \theta \), the
     * variance is \( k \theta^2 \).
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
