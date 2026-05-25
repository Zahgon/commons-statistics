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

import org.apache.commons.numbers.core.DD;

/**
 * Support class for interpolation.
 *
 * @since 1.1
 */
final class Interpolation {

    /**
     * 0.5.
     */
    private static final double HALF = 0.5;

    /**
     * The value 2^53 converted for comparison as an unsigned integer.
     */
    private static final long UNSIGNED_2_POW_53 = Long.MIN_VALUE + (1L << 53);

    /**
     * 2^63.
     */
    private static final double TWO_POW_63 = 0x1.0p63;

    /**
     * No instances.
     */
    private Interpolation() {
    }

    /**
     * Compute the arithmetic mean of the two values taking care to avoid overflow.
     *
     * @param x Value.
     * @param y Value.
     * @return the mean
     */
    static double mean(double x, double y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the arithmetic mean of the two values.
     *
     * @param x Value.
     * @param y Value.
     * @return the mean
     */
    static double mean(int x, int y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the arithmetic mean of the two values as a {@code double}.
     *
     * @param x Value.
     * @param y Value.
     * @return the mean
     */
    static double meanAsDouble(long x, long y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the arithmetic mean of the two values as a {@code long}.
     *
     * <p>The result value is the nearest whole number to the result, with ties
     * rounding towards positive infinity. This is equivalent to the ceiling average.
     *
     * @param x Value.
     * @param y Value.
     * @return the mean
     */
    static long meanAsLong(long x, long y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the arithmetic mean of the two values.
     *
     * <p>The result {@code long} value is the nearest whole number to the result, with ties
     * rounding towards positive infinity. This is equivalent to the ceiling average.
     *
     * @param x Value.
     * @param y Value.
     * @return the mean
     */
    static StatisticResult mean(long x, long y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Linear interpolation between <strong>sorted</strong> values {@code a <= b} using the
     * interpolant {@code t} taking care to avoid overflow.
     *
     * <pre>
     * value = a + t * (b - a)
     * </pre>
     *
     * <p>Note
     *
     * <p>This function has the same properties of as the C++ function <a
     * href="https://en.cppreference.com/w/cpp/numeric/lerp">std::lerp</a> for
     * {@code t in (0, 1)} and {@code b >= a}. It is not a full implementation as it
     * removes explicit checks for {@code t==0} and {@code t==1} and does not support
     * extrapolation as the usage is intended for interpolation of sorted values.
     * The function is monotonic and avoids overflow for finite {@code a} and {@code b}.
     *
     * <p>Interpolation between equal signed infinity arguments will return {@code a}.
     * Alternative implementations may return {@code NaN} for this case. Thus this method
     * interprets infinity values as equivalent and avoids interpolation.
     *
     * @param a Min value.
     * @param b Max value.
     * @param t Interpolant in (0, 1).
     * @return the value
     */
    static double interpolate(double a, double b, double t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Linear interpolation between <strong>sorted</strong> values {@code a <= b} using the
     * interpolant {@code t}.
     *
     * <pre>
     * value = a + t * (b - a)
     * </pre>
     *
     * <p>The {@code long} value is the nearest whole number to the result, with ties
     * rounding towards positive infinity. This value will be in {@code [a, b]}.
     *
     * <p>The {@code double} value is computed within 1 ULP of the exact result.
     * In some cases this may be outside the range {@code [a, b]} due to rounding
     * to a 53-bit representation.
     *
     * <p>Note
     *
     * <p>This function does not support extrapolation as the usage is intended for
     * interpolation of sorted values.
     *
     * @param a Min value.
     * @param b Max value.
     * @param t Interpolant in [0, 1].
     * @return the value
     */
    static StatisticResult interpolate(long a, long b, double t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
