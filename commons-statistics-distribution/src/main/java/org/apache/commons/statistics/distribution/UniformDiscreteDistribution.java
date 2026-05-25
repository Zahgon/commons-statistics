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
import org.apache.commons.rng.sampling.distribution.DiscreteUniformSampler;

/**
 * Implementation of the uniform discrete distribution.
 *
 * <p>The probability mass function of \( X \) is:
 *
 * <p>\[ f(k; a, b) = \frac{1}{b-a+1} \]
 *
 * <p>for integer \( a, b \) and \( a \le b \) and
 * \( k \in [a, b] \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Uniform_distribution_(discrete)">
 * Uniform distribution (discrete) (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/DiscreteUniformDistribution.html">
 * Discrete uniform distribution (MathWorld)</a>
 */
public final class UniformDiscreteDistribution extends AbstractDiscreteDistribution {

    /**
     * Lower bound (inclusive) of this distribution.
     */
    private final int lower;

    /**
     * Upper bound (inclusive) of this distribution.
     */
    private final int upper;

    /**
     * "upper" - "lower" + 1 (as a double to avoid overflow).
     */
    private final double upperMinusLowerPlus1;

    /**
     * Cache of the probability.
     */
    private final double pmf;

    /**
     * Cache of the log probability.
     */
    private final double logPmf;

    /**
     * Value of survival probability for x=0. Used in the inverse survival function.
     */
    private final double sf0;

    /**
     * @param lower Lower bound (inclusive) of this distribution.
     * @param upper Upper bound (inclusive) of this distribution.
     */
    private UniformDiscreteDistribution(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        upperMinusLowerPlus1 = (double) upper - lower + 1;
        pmf = 1.0 / upperMinusLowerPlus1;
        logPmf = -Math.log(upperMinusLowerPlus1);
        sf0 = (upperMinusLowerPlus1 - 1) / upperMinusLowerPlus1;
    }

    /**
     * Creates a new uniform discrete distribution.
     *
     * @param lower Lower bound (inclusive) of this distribution.
     * @param upper Upper bound (inclusive) of this distribution.
     * @return the distribution
     * @throws IllegalArgumentException if {@code lower > upper}.
     */
    public static UniformDiscreteDistribution of(int lower, int upper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(int x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(int x0, int x1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double cumulativeProbability(int x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double survivalProbability(int x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int inverseCumulativeProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int inverseSurvivalProbability(final double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower bound \( a \) and upper bound \( b \), the mean is \( \frac{1}{2} (a + b) \).
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower bound \( a \) and upper bound \( b \), the variance is:
     *
     * <p>\[ \frac{1}{12} (n^2 - 1) \]
     *
     * <p>where \( n = b - a + 1 \).
     */
    @Override
    public double getVariance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is equal to the lower bound parameter
     * of the distribution.
     */
    @Override
    public int getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is equal to the upper bound parameter
     * of the distribution.
     */
    @Override
    public int getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteDistribution.Sampler createSampler(final UniformRandomProvider rng) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
