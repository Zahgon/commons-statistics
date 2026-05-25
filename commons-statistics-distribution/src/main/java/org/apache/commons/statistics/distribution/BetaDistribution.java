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

import org.apache.commons.numbers.gamma.LogBeta;
import org.apache.commons.numbers.gamma.RegularizedBeta;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.ChengBetaSampler;

/**
 * Implementation of the beta distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; \alpha, \beta) = \frac{1}{ B(\alpha, \beta)} x^{\alpha-1} (1-x)^{\beta-1} \]
 *
 * <p>for \( \alpha &gt; 0 \),
 * \( \beta &gt; 0 \), \( x \in [0, 1] \), and
 * the beta function, \( B \), is a normalization constant:
 *
 * <p>\[ B(\alpha, \beta) = \frac{\Gamma(\alpha+\beta)}{\Gamma(\alpha) \Gamma(\beta)} \]
 *
 * <p>where \( \Gamma \) is the gamma function.
 *
 * <p>\( \alpha \) and \( \beta \) are <em>shape</em> parameters.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Beta_distribution">Beta distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/BetaDistribution.html">Beta distribution (MathWorld)</a>
 */
public final class BetaDistribution extends AbstractContinuousDistribution {

    /**
     * First shape parameter.
     */
    private final double alpha;

    /**
     * Second shape parameter.
     */
    private final double beta;

    /**
     * Normalizing factor used in log density computations. log(beta(a, b)).
     */
    private final double logBeta;

    /**
     * Cached value for inverse probability function.
     */
    private final double mean;

    /**
     * Cached value for inverse probability function.
     */
    private final double variance;

    /**
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     */
    private BetaDistribution(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
        logBeta = LogBeta.value(alpha, beta);
        final double alphabetasum = alpha + beta;
        mean = alpha / alphabetasum;
        variance = (alpha * beta) / ((alphabetasum * alphabetasum) * (alphabetasum + 1));
    }

    /**
     * Creates a beta distribution.
     *
     * @param alpha First shape parameter (must be positive).
     * @param beta Second shape parameter (must be positive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code alpha <= 0} or {@code beta <= 0}.
     */
    public static BetaDistribution of(double alpha, double beta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the first shape parameter of this distribution.
     *
     * @return the first shape parameter.
     */
    public double getAlpha() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the second shape parameter of this distribution.
     *
     * @return the second shape parameter.
     */
    public double getBeta() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The density is not defined when {@code x = 0, alpha < 1}, or {@code x = 1, beta < 1}.
     * In this case the limit of infinity is returned.
     */
    @Override
    public double density(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The density is not defined when {@code x = 0, alpha < 1}, or {@code x = 1, beta < 1}.
     * In this case the limit of infinity is returned.
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
     * <p>For first shape parameter \( \alpha \) and second shape parameter
     * \( \beta \), the mean is:
     *
     * <p>\[ \frac{\alpha}{\alpha + \beta} \]
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For first shape parameter \( \alpha \) and second shape parameter
     * \( \beta \), the variance is:
     *
     * <p>\[ \frac{\alpha \beta}{(\alpha + \beta)^2 (\alpha + \beta + 1)} \]
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
     * <p>The upper bound of the support is always 1.
     *
     * @return 1.
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
