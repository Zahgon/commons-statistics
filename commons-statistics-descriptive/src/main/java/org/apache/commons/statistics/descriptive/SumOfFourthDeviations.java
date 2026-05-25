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
package org.apache.commons.statistics.descriptive;

/**
 * Computes the sum of fourth deviations from the sample mean. This
 * statistic is related to the fourth moment.
 *
 * <p>Uses a recursive updating formula as defined in Manca and Marin (2010), equation 16.
 * Note that third term in that equation has been corrected by expansion of the same term
 * from equation 15. Two sum of fourth (quad) deviations (Sq) can be combined using:
 *
 * <p>\[ Sq(X) = {Sq}_1 + {Sq}_2 + \frac{4(m_1 - m_2)(g_1 - g_2) N_1 N_2}{N_1 + N_2}
 *                               + \frac{6(m_1 - m_2)^2(N_2^2 ss_1 + N_1^2 ss_2)}{(N_1 + N_2)^2}
 *                               + \frac{(m_1 - m_2)^4((N_1^2 - N_1 N_2 + N_2^2) N_1 N_2}{(N_1 + N_2)^3} \]
 *
 * <p>where \( N \) is the group size, \( m \) is the mean, \( ss \) is
 * the sum of squared deviations from the mean, and \( g \)
 * is the asymmetrical index where \( g * N \) is the sum of fourth deviations from the mean.
 * Note the term \( ({g_1} - {g_2}) N_1 N_2 == (sc_1 * N_2 - sc_2 * N_1 \)
 * where \( sc \) is the sum of fourth deviations.
 *
 * <p>If \( N_1 \) is size 1 this reduces to:
 *
 * <p>\[ SC_{N+1} = {SC}_N + \frac{4(x - m) -sc}{N + 1}
 *                         + \frac{6(x - m)^2 ss}{(N + 1)^2}
 *                         + \frac{(x - m)^4((1 - N + N^2) N}{(N + 1)^3} \]
 *
 * <p>where \( ss \) is the sum of squared deviations, and \( sc \) is the sum of
 * fourth deviations. This updating formula is identical to that used in
 * {@code org.apache.commons.math3.stat.descriptive.moment.FourthMoment}. The final term
 * uses a rearrangement \( (1 - N + N^2) = (N+1)^2 - 3N \).
 *
 * <p>Supports up to 2<sup>63</sup> (exclusive) observations.
 * This implementation does not check for overflow of the count.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the {@link java.util.function.DoubleConsumer#accept(double) accept} or
 * {@link StatisticAccumulator#combine(StatisticResult) combine} method, it must be synchronized externally.
 *
 * <p>However, it is safe to use {@link java.util.function.DoubleConsumer#accept(double) accept}
 * and {@link StatisticAccumulator#combine(StatisticResult) combine}
 * as {@code accumulator} and {@code combiner} functions of
 * {@link java.util.stream.Collector Collector} on a parallel stream,
 * because the parallel implementation of {@link java.util.stream.Stream#collect Stream.collect()}
 * provides the necessary partitioning, isolation, and merging of results for
 * safe and efficient parallel execution.
 *
 * <p>References:
 * <ul>
 *   <li>Manca and Marin (2020)
 *       Decomposition of the Sum of Cubes, the Sum Raised to the
 *       Power of Four and Codeviance.
 *       Applied Mathematics, 11, 1013-1020.
 *       <a href="https://doi.org/10.4236/am.2020.1110067">doi: 10.4236/am.2020.1110067</a></li>
 * </ul>
 *
 * @since 1.1
 */
class SumOfFourthDeviations extends SumOfCubedDeviations {

    /**
     * Sum of forth deviations of the values that have been added.
     */
    private double sumFourthDev;

    /**
     * Create an instance.
     */
    SumOfFourthDeviations() {
        // No-op
    }

    /**
     * Create an instance with the given sum of fourth and squared deviations.
     *
     * @param sq Sum of fourth (quad) deviations.
     * @param sc Sum of fourth deviations.
     */
    private SumOfFourthDeviations(double sq, SumOfCubedDeviations sc) {
        super(sc);
        this.sumFourthDev = sq;
    }

    /**
     * Create an instance with the given sum of cubed and squared deviations,
     * and first moment.
     *
     * @param sq Sum of fouth deviations.
     * @param sc Sum of cubed deviations.
     * @param ss Sum of squared deviations.
     * @param m1 First moment.
     * @param n Count of values.
     */
    private SumOfFourthDeviations(double sq, double sc, double ss, double m1, long n) {
        super(sc, ss, m1, n);
        this.sumFourthDev = sq;
    }

    /**
     * Returns an instance populated using the input {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept accept} may be
     * different from this instance.
     *
     * @param values Values.
     * @return {@code SumOfFourthDeviations} instance.
     */
    static SumOfFourthDeviations of(double... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an instance populated using the specified range of {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept accept} may be
     * different from this instance.
     *
     * <p>Warning: No range checks are performed.
     *
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return {@code SumOfFourthDeviations} instance.
     * @since 1.2
     */
    static SumOfFourthDeviations ofRange(double[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the sum of fourth deviations.
     *
     * <p>Uses the provided {@code sum} to create the first moment.
     * This method is used by {@link DoubleStatistics} using a sum that can be reused
     * for the {@link Sum} statistic.
     *
     * <p>Warning: No range checks are performed.
     *
     * @param sum Sum of the values.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return {@code SumOfFourthDeviations} instance.
     */
    static SumOfFourthDeviations createFromRange(org.apache.commons.numbers.core.Sum sum, double[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the sum of fourth deviations.
     *
     * @param sc Sum of cubed deviations.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return {@code SumOfFourthDeviations} instance.
     */
    private static SumOfFourthDeviations create(SumOfCubedDeviations sc, double[] values, int from, int to) {
        // Edge cases
        final double xbar = sc.getFirstMoment();
        if (!Double.isFinite(xbar) || !Double.isFinite(sc.sumSquaredDev) || !Double.isFinite(sc.sumCubedDev)) {
            // Overflow computing lower order deviations will overflow
            return new SumOfFourthDeviations(Double.NaN, sc);
        }
        // Compute the sum of fourth (quad) deviations.
        // Note: This handles n=1.
        double s = 0;
        for (int i = from; i < to; i++) {
            s += pow4(values[i] - xbar);
        }
        return new SumOfFourthDeviations(s, sc);
    }

    /**
     * Compute {@code x^4}.
     * Uses compound multiplication.
     *
     * @param x Value.
     * @return x^4
     */
    private static double pow4(double x) {
        final double x2 = x * x;
        return x2 * x2;
    }

    /**
     * Returns an instance populated using the input {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept(double) accept} may be
     * different from this instance.
     *
     * @param values Values.
     * @return {@code SumOfCubedDeviations} instance.
     */
    static SumOfFourthDeviations of(int... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an instance populated using the specified range of {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept(double) accept} may be
     * different from this instance.
     *
     * <p>Warning: No range checks are performed.
     *
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return {@code SumOfFourthDeviations} instance.
     */
    static SumOfFourthDeviations ofRange(int[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an instance populated using the input {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept(double) accept} may be
     * different from this instance.
     *
     * @param values Values.
     * @return {@code SumOfFourthDeviations} instance.
     */
    static SumOfFourthDeviations of(long... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an instance populated using the specified range of {@code values}.
     *
     * <p>Note: {@code SumOfFourthDeviations} computed using {@link #accept(double) accept} may be
     * different from this instance.
     *
     * <p>Warning: No range checks are performed.
     *
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return {@code SumOfFourthDeviations} instance.
     */
    static SumOfFourthDeviations ofRange(long[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Updates the state of the statistic to reflect the addition of {@code value}.
     *
     * @param value Value.
     */
    @Override
    public void accept(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the sum of fourth deviations of all input values.
     *
     * <p>Note that the result should be positive. However the updating sum is subject to
     * cancellation of potentially large positive and negative terms. Overflow of these
     * terms can result in a sum of opposite signed infinities and a {@code NaN} result
     * for finite input values where the correct result is positive infinity.
     *
     * <p>Note: Any non-finite result should be considered a failed computation. The
     * result is returned as computed and not consolidated to a single NaN. This is done
     * for testing purposes to allow the result to be reported. It is possible to track
     * input values to finite/non-finite (e.g. using bit mask manipulation of the exponent
     * field). However this statistic in currently used in the kurtosis and in the case
     * of failed computation distinguishing a non-finite result is not useful.
     *
     * @return sum of fourth deviations of all values.
     */
    double getSumOfFourthDeviations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Combines the state of another {@code SumOfFourthDeviations} into this one.
     *
     * @param other Another {@code SumOfFourthDeviations} to be combined.
     * @return {@code this} instance after combining {@code other}.
     */
    SumOfFourthDeviations combine(SumOfFourthDeviations other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
