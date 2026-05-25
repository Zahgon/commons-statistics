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

import java.util.Set;

/**
 * Argument validation methods.
 *
 * @since 1.1
 */
final class Arguments {

    /**
     * Two.
     */
    private static final int TWO = 2;

    /**
     * No instances.
     */
    private Arguments() {
    }

    /**
     * Check the significance level is in the correct range.
     *
     * @param alpha Significance level of the test.
     * @throws IllegalArgumentException if {@code alpha} is not in the range
     * {@code (0, 0.5]}
     */
    static void checkSignificance(double alpha) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that the value is {@code >= 0}.
     *
     * @param v Value to be tested.
     * @return the value
     * @throws IllegalArgumentException if the value is less than 0.
     */
    static int checkNonNegative(int v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that the value is {@code >= 0}.
     *
     * @param v Value to be tested.
     * @throws IllegalArgumentException if the value is less than 0.
     */
    static void checkNonNegative(double v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that all values are {@code >= 0}.
     *
     * @param values Values to be tested.
     * @throws IllegalArgumentException if any values are less than 0.
     */
    static void checkNonNegative(long[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that all values are {@code >= 0}.
     *
     * @param values Values to be tested.
     * @throws IllegalArgumentException if any values are less than 0.
     */
    static void checkNonNegative(long[][] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that value is {@code > 0}.
     *
     * @param v Value to be tested.
     * @return the value
     * @throws IllegalArgumentException if the value is not strictly positive.
     */
    static int checkStrictlyPositive(int v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that value is {@code > 0}.
     *
     * @param v Value to be tested.
     * @return the value
     * @throws IllegalArgumentException if the value is not strictly positive.
     */
    static double checkStrictlyPositive(double v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that all values are {@code > 0}.
     *
     * @param values Values to be tested.
     * @throws IllegalArgumentException if any values are not strictly positive.
     */
    static void checkStrictlyPositive(double[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that the value is finite.
     *
     * @param v Value to be tested.
     * @return the value
     * @throws IllegalArgumentException if the value is not finite.
     */
    static double checkFinite(double v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check that all values are not {@link Double#NaN}.
     *
     * @param values Values to be tested.
     * @throws IllegalArgumentException if any values are NaN.
     */
    static void checkNonNaN(double[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the input array is rectangular. It is assumed the array is non-null
     * and has a non-zero length.
     *
     * @param array Array to be tested.
     * @throws NullPointerException if input array is null
     * @throws IndexOutOfBoundsException if input array is zero length
     * @throws IllegalArgumentException if input array is not rectangular
     */
    static void checkRectangular(long[][] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the values size is the minimum required, {@code size >= required}.
     *
     * @param size Values size.
     * @param required Required size.
     * @throws IllegalArgumentException if {@code size < required}
     */
    static void checkValuesRequiredSize(int size, int required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the categories size is the minimum required, {@code size >= required}.
     *
     * @param size Values size.
     * @param required Required size.
     * @throws IllegalArgumentException if {@code size < required}
     */
    static void checkCategoriesRequiredSize(int size, int required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the values sizes are equal, {@code size1 == size2}.
     *
     * @param size1 First size.
     * @param size2 Second size.
     * @throws IllegalArgumentException if {@code size1 != size2}
     */
    static void checkValuesSizeMatch(int size1, int size2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the option is allowed.
     *
     * @param <E> Option type.
     * @param v Option value.
     * @param allowed Allowed options.
     * @return the value
     * @throws IllegalArgumentException if the value is not in the allowed options or is null
     */
    static <E extends Enum<E>> E checkOption(E v, Set<E> allowed) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the input is a 2-by-2 contingency table.
     *
     * @param table Table.
     * @throws IllegalArgumentException if the {@code table} is not a 2-by-2 table; any
     * table entry is negative; or the sum is zero or is not an integer
     */
    static void checkTable(int[][] table) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
