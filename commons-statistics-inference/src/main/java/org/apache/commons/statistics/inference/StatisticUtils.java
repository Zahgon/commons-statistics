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
package org.apache.commons.statistics.inference;

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.numbers.core.DD;
import org.apache.commons.numbers.core.Precision;
import org.apache.commons.numbers.core.Sum;
import org.apache.commons.statistics.descriptive.Mean;

/**
 * Utility computation methods.
 *
 * @since 1.1
 */
final class StatisticUtils {

    /**
     * No instances.
     */
    private StatisticUtils() {
    }

    /**
     * Compute {@code x - y}.
     *
     * <p>If {@code y} is zero the original array is returned, else a new array is created
     * with the difference.
     *
     * @param x Array.
     * @param y Value.
     * @return x - y
     * @throws NullPointerException if {@code x} is null and {@code y} is non-zero
     */
    static double[] subtract(double[] x, double y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the degrees of freedom as {@code n - 1 - m}.
     *
     * <p>This method is common functionality shared between the Chi-square test and
     * G-test. The pre-conditions for those tests are performed by this method.
     *
     * @param n Number of observations.
     * @param m Adjustment (assumed to be positive).
     * @return the degrees of freedom
     * @throws IllegalArgumentException if the degrees of freedom is not strictly positive
     */
    static int computeDegreesOfFreedom(int n, int m) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the ratio between the sum of the observed and expected values.
     * The ratio can be used to scale the expected values to have the same sum
     * as the observed values:
     *
     * <pre>
     * sum(o) = sum(e * ratio)
     * </pre>
     *
     * <p>This method is common functionality shared between the Chi-square test and
     * G-test. The pre-conditions for those tests are performed by this method.
     *
     * @param expected Expected values.
     * @param observed Observed values.
     * @return the ratio
     * @throws IllegalArgumentException if the sample size is less than 2; the array
     * sizes do not match; {@code expected} has entries that are not strictly
     * positive; {@code observed} has negative entries; or all the observations are zero.
     */
    static double computeRatio(double[] expected, long[] observed) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the value to the sum.
     *
     * @param sum Sum.
     * @param v Value.
     * @return the new sum
     */
    private static DD add(DD sum, long v) {
        // The condition here is a high probability branch if the sample is
        // frequency counts which are typically in the 32-bit integer range,
        // i.e. all the upper bits are zero.
        return (v >>> Integer.SIZE) == 0 ? sum.add(v) : sum.add(DD.of(v));
    }

    // Specialised statistic methods not directly supported by o.a.c.statistics.descriptive
    /**
     * Returns the arithmetic mean of the entries in the input arrays,
     * or {@code NaN} if the combined length of the arrays is zero.
     *
     * <p>Supports a combined length above the maximum array size.
     *
     * <p>A two-pass, corrected algorithm is used, starting with the definitional formula
     * computed using the array of stored values and then correcting this by adding the
     * mean deviation of the data values from the arithmetic mean. See, e.g. "Comparison
     * of Several Algorithms for Computing Sample Means and Variances," Robert F. Ling,
     * Journal of the American Statistical Association, Vol. 69, No. 348 (Dec., 1974), pp.
     * 859-866.
     *
     * @param samples Values.
     * @return the mean of the values or NaN if length = 0
     */
    static double mean(Collection<double[]> samples) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the mean of the (signed) differences between corresponding elements of the
     * input arrays.
     *
     * <pre>
     * sum(x[i] - y[i]) / x.length
     * </pre>
     *
     * <p>This method avoids intermediate array allocation.
     *
     * @param x First array.
     * @param y Second array.
     * @return mean of paired differences
     * @throws IllegalArgumentException if the arrays do not have the same length.
     */
    static double meanDifference(double[] x, double[] y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the variance of the (signed) differences between corresponding elements of
     * the input arrays, or {@code NaN} if the arrays are empty.
     *
     * <pre>
     * var(x[i] - y[i])
     * </pre>
     *
     * <p>Returns the bias-corrected sample variance (using {@code n - 1} in the denominator).
     * Returns 0 for a single-value (i.e. length = 1) sample.
     *
     * <p>This method avoids intermediate array allocation.
     *
     * <p>Uses a two-pass algorithm. Specifically, these methods use the "corrected
     * two-pass algorithm" from Chan, Golub, Levesque, <i>Algorithms for Computing the
     * Sample Variance</i>, American Statistician, vol. 37, no. 3 (1983) pp.
     * 242-247.
     *
     * @param x First array.
     * @param y Second array.
     * @param mean the mean difference between corresponding entries
     * @return variance of paired differences
     * @throws IllegalArgumentException if the arrays do not have the same length.
     * @see #meanDifference(double[], double[])
     */
    static double varianceDifference(double[] x, double[] y, double mean) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
