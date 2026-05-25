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
import org.apache.commons.rng.sampling.distribution.ContinuousUniformSampler;
import org.apache.commons.rng.sampling.distribution.SharedStateContinuousSampler;

/**
 * Implementation of the log-uniform distribution. This is also known as the reciprocal distribution.
 *
 * <p>The probability density function of \( X \) is:
 *
 * <p>\[ f(x; a, b) = \frac{1}{x \ln \frac b a} \]
 *
 * <p>for \( 0 \lt a \lt b \lt \infty \) and
 * \( x \in [a, b] \).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Reciprocal_distribution">Reciprocal distribution (Wikipedia)</a>
 * @since 1.1
 */
public final class LogUniformDistribution extends AbstractContinuousDistribution {

    /**
     * Lower bound (a) of this distribution (inclusive).
     */
    private final double lower;

    /**
     * Upper bound (b) of this distribution (exclusive).
     */
    private final double upper;

    /**
     * log(a).
     */
    private final double logA;

    /**
     * log(b).
     */
    private final double logB;

    /**
     * log(b) - log(a).
     */
    private final double logBmLogA;

    /**
     * log(log(b) - log(a)).
     */
    private final double logLogBmLogA;

    /**
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (inclusive).
     */
    private LogUniformDistribution(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
        logA = Math.log(lower);
        logB = Math.log(upper);
        logBmLogA = logB - logA;
        logLogBmLogA = Math.log(logBmLogA);
    }

    /**
     * Creates a log-uniform distribution.
     *
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (inclusive).
     * @return the distribution
     * @throws IllegalArgumentException if {@code lower >= upper}; the range between the bounds
     * is not finite; or {@code lower <= 0}
     */
    public static LogUniformDistribution of(double lower, double upper) {
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

    @Override
    public double inverseSurvivalProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For lower bound \( a \) and upper bound \( b \), the mean is:
     *
     * <p>\[ \frac{b - a}{\ln \frac b a} \]
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
     * <p>\[ \frac{b^2 - a^2}{2 \ln \frac b a} - \left( \frac{b - a}{\ln \frac b a} \right)^2 \]
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
    public double getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is equal to the upper bound parameter
     * of the distribution.
     */
    @Override
    public double getSupportUpperBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clip the value to the range [lower, upper].
     * This is used to handle floating-point error at the support bound.
     *
     * @param x Value x
     * @return x clipped to the range
     */
    private double clipToRange(double x) {
        return clip(x, lower, upper);
    }

    /**
     * Clip the value to the range [lower, upper].
     *
     * @param x Value x
     * @param lower Lower bound (inclusive)
     * @param upper Upper bound (inclusive)
     * @return x clipped to the range
     */
    private static double clip(double x, double lower, double upper) {
        if (x <= lower) {
            return lower;
        }
        return x < upper ? x : upper;
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
