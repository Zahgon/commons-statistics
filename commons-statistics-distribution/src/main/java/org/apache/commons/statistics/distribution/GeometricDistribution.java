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

import java.util.function.IntToDoubleFunction;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.GeometricSampler;

/**
 * Implementation of the geometric distribution.
 *
 * <p>The probability mass function of \( X \) is:
 *
 * <p>\[ f(k; p) = (1-p)^k \, p \]
 *
 * <p>for \( p \in (0, 1] \) the probability of success and
 * \( k \in \{0, 1, 2, \dots\} \) the number of failures.
 *
 * <p>This parameterization is used to model the number of failures until
 * the first success.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Geometric_distribution">Geometric distribution (Wikipedia)</a>
 * @see <a href="https://mathworld.wolfram.com/GeometricDistribution.html">Geometric distribution (MathWorld)</a>
 */
public final class GeometricDistribution extends AbstractDiscreteDistribution {

    /**
     * 1/2.
     */
    private static final double HALF = 0.5;

    /**
     * The probability of success.
     */
    private final double probabilityOfSuccess;

    /**
     * {@code log(p)} where p is the probability of success.
     */
    private final double logProbabilityOfSuccess;

    /**
     * {@code log(1 - p)} where p is the probability of success.
     */
    private final double log1mProbabilityOfSuccess;

    /**
     * Value of survival probability for x=0.
     * Used in the survival functions. Equal to (1 - probability of success).
     */
    private final double sf0;

    /**
     * Implementation of PMF(x). Assumes that {@code x > 0}.
     */
    private final IntToDoubleFunction pmf;

    /**
     * @param p Probability of success.
     */
    private GeometricDistribution(double p) {
        probabilityOfSuccess = p;
        logProbabilityOfSuccess = Math.log(p);
        log1mProbabilityOfSuccess = Math.log1p(-p);
        sf0 = 1 - p;
        // Choose the PMF implementation.
        // When p >= 0.5 then 1 - p is exact and using the power function
        // is consistently more accurate than the use of the exponential function.
        // When p -> 0 then the exponential function avoids large error propagation
        // of the power function used with an inexact 1 - p.
        // Also compute the survival probability for use when x=0.
        if (p >= HALF) {
            pmf = x -> Math.pow(sf0, x) * probabilityOfSuccess;
        } else {
            pmf = x -> Math.exp(log1mProbabilityOfSuccess * x) * probabilityOfSuccess;
        }
    }

    /**
     * Creates a geometric distribution.
     *
     * @param p Probability of success.
     * @return the geometric distribution
     * @throws IllegalArgumentException if {@code p <= 0} or {@code p > 1}.
     */
    public static GeometricDistribution of(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the probability of success parameter of this distribution.
     *
     * @return the probability of success.
     */
    public double getProbabilityOfSuccess() {
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
    public int inverseSurvivalProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For probability parameter \( p \), the mean is:
     *
     * <p>\[ \frac{1 - p}{p} \]
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For probability parameter \( p \), the variance is:
     *
     * <p>\[ \frac{1 - p}{p^2} \]
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
    public int getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is positive infinity except for the
     * probability parameter {@code p = 1.0}.
     *
     * @return {@link Integer#MAX_VALUE} or 0.
     */
    @Override
    public int getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sampler createSampler(UniformRandomProvider rng) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
