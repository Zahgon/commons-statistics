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
import org.apache.commons.rng.sampling.distribution.RejectionInversionZipfSampler;

/**
 * Implementation of the Zipf distribution.
 *
 * <p>The probability mass function of \( X \) is:
 *
 * <p>\[ f(k; N, s) = \frac{1/k^s}{H_{N,s}} \]
 *
 * <p>for \( N \in \{1, 2, 3, \dots\} \) the number of elements,
 * \( s \gt 0 \) the exponent characterizing the distribution,
 * \( k \in \{1, 2, \dots, N\} \) the element rank, and
 * \( H_{N,s} \) is the normalizing constant which corresponds to the
 * <a href="https://en.wikipedia.org/wiki/Harmonic_number#Generalized_harmonic_numbers">
 * generalized harmonic number</a> of order N of s.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Zipf's_law">Zipf distribution (Wikipedia)</a>
 */
public final class ZipfDistribution extends AbstractDiscreteDistribution {

    /**
     * Number of elements.
     */
    private final int numberOfElements;

    /**
     * Exponent parameter of the distribution.
     */
    private final double exponent;

    /**
     * Cached value of the nth generalized harmonic.
     */
    private final double nthHarmonic;

    /**
     * Cached value of the log of the nth generalized harmonic.
     */
    private final double logNthHarmonic;

    /**
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     */
    private ZipfDistribution(int numberOfElements, double exponent) {
        this.numberOfElements = numberOfElements;
        this.exponent = exponent;
        this.nthHarmonic = generalizedHarmonic(numberOfElements, exponent);
        logNthHarmonic = Math.log(nthHarmonic);
    }

    /**
     * Creates a Zipf distribution.
     *
     * @param numberOfElements Number of elements.
     * @param exponent Exponent.
     * @return the distribution
     * @exception IllegalArgumentException if {@code numberOfElements <= 0}
     * or {@code exponent <= 0}.
     */
    public static ZipfDistribution of(int numberOfElements, double exponent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the number of elements parameter of this distribution.
     *
     * @return the number of elements.
     */
    public int getNumberOfElements() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the exponent parameter of this distribution.
     *
     * @return the exponent.
     */
    public double getExponent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(final int x) {
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
    public double cumulativeProbability(final int x) {
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
     *
     * <p>For number of elements \( N \) and exponent \( s \), the mean is:
     *
     * <p>\[ \frac{H_{N,s-1}}{H_{N,s}} \]
     *
     * <p>where \( H_{N,k} \) is the
     * <a href="https://en.wikipedia.org/wiki/Harmonic_number#Generalized_harmonic_numbers">
     * generalized harmonic number</a> of order \( N \) of \( k \).
     */
    @Override
    public double getMean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>For number of elements \( N \) and exponent \( s \), the variance is:
     *
     * <p>\[ \frac{H_{N,s-2}}{H_{N,s}} - \frac{H_{N,s-1}^2}{H_{N,s}^2} \]
     *
     * <p>where \( H_{N,k} \) is the
     * <a href="https://en.wikipedia.org/wiki/Harmonic_number#Generalized_harmonic_numbers">
     * generalized harmonic number</a> of order \( N \) of \( k \).
     */
    @Override
    public double getVariance() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Calculates the Nth generalized harmonic number. See
     * <a href="https://mathworld.wolfram.com/HarmonicSeries.html">Harmonic
     * Series</a>.
     *
     * <p>Assumes {@code exponent > 0} to arrange the terms to sum from small to large.
     *
     * @param n Term in the series to calculate (must be larger than 1)
     * @param m Exponent (special case {@code m = 1} is the harmonic series).
     * @return the n<sup>th</sup> generalized harmonic number.
     */
    private static double generalizedHarmonic(final int n, final double m) {
        double value = 0;
        // Sum small to large
        for (int k = n; k >= 1; k--) {
            value += Math.pow(k, -m);
        }
        return value;
    }

    /**
     * Calculates the Nth generalized harmonic number.
     *
     * <p>Checks the value of the {@code exponent} to arrange the terms to sum from from small to large.
     *
     * @param n Term in the series to calculate (must be larger than 1)
     * @param m Exponent (special case {@code m = 1} is the harmonic series).
     * @return the n<sup>th</sup> generalized harmonic number.
     */
    private static double generalizedHarmonicAscendingSum(final int n, final double m) {
        double value = 0;
        // Sum small to large
        // If m < 0 then sum ascending, otherwise descending
        if (m < 0) {
            for (int k = 1; k <= n; k++) {
                value += Math.pow(k, -m);
            }
        } else {
            for (int k = n; k >= 1; k--) {
                value += Math.pow(k, -m);
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The lower bound of the support is always 1.
     *
     * @return 1.
     */
    @Override
    public int getSupportLowerBound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * <p>The upper bound of the support is the number of elements.
     *
     * @return number of elements.
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
