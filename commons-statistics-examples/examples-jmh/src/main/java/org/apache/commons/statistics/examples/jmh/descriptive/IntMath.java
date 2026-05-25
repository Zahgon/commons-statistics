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
package org.apache.commons.statistics.examples.jmh.descriptive;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Support class for integer math.
 *
 * <p>This is a copy of {@code o.a.c.statistics.descriptive.IntMath} to allow benchmarking.
 * Additional methods may have been added for comparative benchmarks.
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
     * <p>Note: The method uses the unsigned multiplication. When the input is negative
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
     * @param a Value.
     * @param b Value.
     * @return the double
     */
    static double unsignedMultiplyToDoubleBigInteger(long a, long b) {
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
    static double uin128ToDouble(long hi, long lo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
