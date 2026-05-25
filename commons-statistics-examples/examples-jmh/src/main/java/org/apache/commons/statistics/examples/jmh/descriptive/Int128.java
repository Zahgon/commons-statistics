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
import org.apache.commons.numbers.core.DD;

/**
 * A mutable 128-bit signed integer.
 *
 * <p>This is a copy of {@code o.a.c.statistics.descriptive.Int128} to allow benchmarking.
 * Additional methods may have been added for comparative benchmarks.
 *
 * <p>This is a specialised class to implement an accumulator of {@code long} values.
 *
 * <p>Note: This number uses a signed long integer representation of:
 *
 * <pre>value = 2<sup>64</sup> * hi64 + lo64</pre>
 *
 * <p>If the high value is zero then the low value is the long representation of the
 * number including the sign bit. Otherwise the low value corresponds to a correction
 * term for the scaled high value which contains the sign-bit of the number.
 *
 * @since 1.1
 */
final class Int128 {

    /**
     * Mask for the lower 32-bits of a long.
     */
    private static final long MASK32 = 0xffff_ffffL;

    /**
     * low 64-bits.
     */
    private long lo;

    /**
     * high 64-bits.
     */
    private long hi;

    /**
     * Create an instance.
     */
    private Int128() {
        // No-op
    }

    /**
     * Create an instance.
     *
     * @param x Value.
     */
    private Int128(long x) {
        lo = x;
    }

    /**
     * Create an instance using a direct binary representation.
     * This is package-private for testing.
     *
     * @param hi High 64-bits.
     * @param lo Low 64-bits.
     */
    Int128(long hi, long lo) {
        this.lo = lo;
        this.hi = hi;
    }

    /**
     * Create an instance. The initial value is zero.
     *
     * @return the instance
     */
    static Int128 create() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create an instance of the {@code long} value.
     *
     * @param x Value.
     * @return the instance
     */
    static Int128 of(long x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the value.
     *
     * @param x Value.
     */
    void add(long x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the value.
     *
     * @param x Value.
     */
    void add2(long x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the value.
     *
     * @param x Value.
     */
    void add(Int128 x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the square of the low 64-bits of this number.
     *
     * <p>Warning: This ignores the upper 64-bits. Use with caution.
     *
     * @return the square
     */
    UInt128 squareLow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the square of this number.
     *
     * @return the square
     */
    BigInteger square() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convert to a BigInteger.
     *
     * @return the value
     */
    BigInteger toBigInteger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convert to a double-double.
     *
     * @return the value
     */
    DD toDD() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the lower 64-bits as a {@code long} value.
     *
     * <p>If the high value is zero then the low value is the long representation of the
     * number including the sign bit. Otherwise this value corresponds to a correction
     * term for the scaled high value which contains the sign-bit of the number
     * (see {@link Int128}).
     *
     * @return the low 64-bits
     */
    long lo64() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the higher 64-bits as a {@code long} value.
     *
     * @return the high 64-bits
     * @see #lo64()
     */
    long hi64() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
