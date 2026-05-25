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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Support class for integer math.
 *
 * @since 1.1
 */
final class IntMath {

    /**
     * Mask for the lower 32-bits of a long.
     */
    private static final long MASK32 = 0xffff_ffffL;

    /**
     * Mask for the lower 52-bits of a long.
     */
    private static final long MASK52 = 0xf_ffff_ffff_ffffL;

    /**
     * Bias offset for the exponent of a double.
     */
    private static final int EXP_BIAS = 1023;

    /**
     * Shift for the exponent of a double.
     */
    private static final int EXP_SHIFT = 52;

    /**
     * 0.5.
     */
    private static final double HALF = 0.5;

    /**
     * No instances.
     */
    private IntMath() {
    }

    /**
     * Square the values as if an unsigned 64-bit long to produce the high 64-bits
     * of the 128-bit unsigned result.
     *
     * <p>This method computes the equivalent of:
     * <pre>{@code
     * Math.multiplyHigh(x, x)
     * Math.unsignedMultiplyHigh(x, x) - (((x >> 63) & x) << 1)
     * }</pre>
     *
     * <p>Note: The method {@code Math.multiplyHigh} was added in JDK 9
     * and should be used as above when the source code targets Java 11
     * to exploit the intrinsic method.
     *
     * <p>Note: The method uses unsigned multiplication. When the input is negative
     * it can be adjusted to the signed result by subtracting the argument twice from the
     * result.
     *
     * @param x Value
     * @return the high 64-bits of the 128-bit result
     */
    static long squareHigh(long x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Multiply the two values as if unsigned 64-bit longs to produce the high 64-bits
     * of the 128-bit unsigned result.
     *
     * <p>This method computes the equivalent of:
     * <pre>{@code
     * Math.multiplyHigh(a, b) + ((a >> 63) & b) + ((b >> 63) & a)
     * }</pre>
     *
     * <p>Note: The method {@code Math.multiplyHigh} was added in JDK 9
     * and should be used as above when the source code targets Java 11
     * to exploit the intrinsic method.
     *
     * <p>Note: The method {@code Math.unsignedMultiplyHigh} was added in JDK 18
     * and should be used when the source code target allows.
     *
     * <p>Taken from {@code o.a.c.rng.core.source64.LXMSupport}.
     *
     * @param value1 the first value
     * @param value2 the second value
     * @return the high 64-bits of the 128-bit result
     */
    static long unsignedMultiplyHigh(long value1, long value2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Multiply the arguments as if unsigned integers to a {@code double} result.
     *
     * @param x Value.
     * @param y Value.
     * @return the double
     */
    static double unsignedMultiplyToDouble(long x, long y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convert an unsigned 128-bit integer to a {@code double}.
     *
     * @param hi High 64-bits.
     * @param lo Low 64-bits.
     * @return the double
     */
    static double uint128ToDouble(long hi, long lo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the whole number that is nearest to the {@code double} argument {@code x}
     * as an {@code int}, with ties rounding towards positive infinity.
     *
     * <p>This will raise an {@link ArithmeticException} if the closest
     * integer result is not within the range {@code [-2^31, 2^31)},
     * i.e. it overflows an {@code int}; or the argument {@code x}
     * is not finite.
     *
     * <p>Note: This method is equivalent to:
     * <pre>
     * Math.toIntExact(Math.round(x))
     * </pre>
     *
     * <p>The behaviour has been re-implemented for consistent error handling
     * for {@code int}, {@code long} and {@code BigInteger} types.
     *
     * @param x Value.
     * @return rounded value
     * @throws ArithmeticException if the {@code result} overflows an {@code int},
     * or {@code x} is not finite
     * @see Math#round(double)
     * @see Math#toIntExact(long)
     */
    static int toIntExact(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the whole number that is nearest to the {@code double} argument {@code x}
     * as a {@code long}, with ties rounding towards positive infinity.
     *
     * <p>This will raise an {@link ArithmeticException} if the closest
     * integer result is not within the range {@code [-2^63, 2^63)},
     * i.e. it overflows a {@code long}; or the argument {@code x}
     * is not finite.
     *
     * @param x Value.
     * @return rounded value
     * @throws ArithmeticException if the {@code result} overflows a {@code long},
     * or {@code x} is not finite
     */
    static long toLongExact(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the whole number that is nearest to the {@code double} argument {@code x}
     * as a {@code BigInteger}, with ties rounding towards positive infinity.
     *
     * <p>This will raise an {@link ArithmeticException} if the argument {@code x}
     * is not finite.
     *
     * @param x Value.
     * @return rounded value
     * @throws ArithmeticException if {@code x} is not finite
     */
    static BigInteger toBigIntegerExact(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the whole number that is the nearest to x, with ties rounding towards positive infinity.
     *
     * <p>This method is intended to perform the equivalent of
     * {@link Math#round(double)} without converting to a {@code long} primitive type.
     * This allows the domain of the result to be checked against the range {@code [-2^63, 2^63)}.
     *
     * <p>Note: Adapted from {@code o.a.c.math4.AccurateMath.rint} and
     * modified to perform rounding towards positive infinity.
     *
     * @param x Number from which nearest whole number is requested.
     * @return a double number r such that r is an integer {@code r - 0.5 <= x < r + 0.5}
     */
    private static double roundToInteger(double x) {
        final double y = Math.floor(x);
        final double d = x - y;
        if (d >= HALF) {
            // Here we do not preserve the sign of the operand in the case
            // of -0.5 < x <= -0.0 since the rounded result is required as an integer.
            // if y == -1.0:
            //    return -0.0
            return y + 1.0;
        }
        return y;
    }
}
