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

import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * Utility methods for statistics.
 *
 * @since 1.1
 */
final class Statistics {

    /**
     * A no-operation double consumer. This is exposed for testing.
     */
    static final DoubleConsumer DOUBLE_NOOP = new DoubleConsumer() {

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public DoubleConsumer andThen(DoubleConsumer after) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    /**
     * A no-operation int consumer. This is exposed for testing.
     */
    static final IntConsumer INT_NOOP = new IntConsumer() {

        @Override
        public void accept(int value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IntConsumer andThen(IntConsumer after) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    /**
     * A no-operation long consumer. This is exposed for testing.
     */
    static final LongConsumer LONG_NOOP = new LongConsumer() {

        @Override
        public void accept(long value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public LongConsumer andThen(LongConsumer after) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    /**
     * Error message for an incompatible statistics.
     */
    private static final String INCOMPATIBLE_STATISTICS = "Incompatible statistics";

    /**
     * No instances.
     */
    private Statistics() {
    }

    /**
     * Add all the {@code values} to the {@code statistic}.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, double[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add the specified range of {@code values} to the {@code statistic}.
     * <p>Warning: No range checks are performed.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, double[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add all the {@code values} to the {@code statistic}.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, int[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add the specified range of {@code values} to the {@code statistic}.
     * <p>Warning: No range checks are performed.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, int[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add all the {@code values} to the {@code statistic}.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, long[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add the specified range of {@code values} to the {@code statistic}.
     * <p>Warning: No range checks are performed.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the statistic
     */
    static <T extends DoubleConsumer> T add(T statistic, long[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add all the {@code values} to the {@code statistic}.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @return the statistic
     */
    static <T extends IntConsumer> T add(T statistic, int[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add the specified range of {@code values} to the {@code statistic}.
     * <p>Warning: No range checks are performed.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the statistic
     */
    static <T extends IntConsumer> T add(T statistic, int[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add all the {@code values} to the {@code statistic}.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @return the statistic
     */
    static <T extends LongConsumer> T add(T statistic, long[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add the specified range of {@code values} to the {@code statistic}.
     * <p>Warning: No range checks are performed.
     *
     * @param <T> Type of the statistic
     * @param statistic Statistic.
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the statistic
     */
    static <T extends LongConsumer> T add(T statistic, long[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if the second central moment {@code m2} is effectively
     * zero given the magnitude of the first raw moment {@code m1}.
     *
     * <p>This method shares the logic for detecting a zero variance among implementations
     * that divide by the variance (e.g. skewness, kurtosis).
     *
     * @param m1 First raw moment (mean).
     * @param m2 Second central moment (biased variance).
     * @return true if the variance is zero
     */
    static boolean zeroVariance(double m1, double m2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Chain the {@code consumers} into a single composite consumer. Ignore any {@code null}
     * consumer. Returns {@code null} if all arguments are {@code null}.
     *
     * @param consumers Consumers.
     * @return a composed consumer (or null)
     */
    static DoubleConsumer composeDoubleConsumers(DoubleConsumer... consumers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Chain the {@code consumers} into a single composite consumer. Ignore any {@code null}
     * consumer. Returns {@code null} if all arguments are {@code null}.
     *
     * @param consumers Consumers.
     * @return a composed consumer (or null)
     */
    static IntConsumer composeIntConsumers(IntConsumer... consumers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Chain the {@code consumers} into a single composite consumer. Ignore any {@code null}
     * consumer. Returns {@code null} if all arguments are {@code null}.
     *
     * @param consumers Consumers.
     * @return a composed consumer (or null)
     */
    static LongConsumer composeLongConsumers(LongConsumer... consumers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the statistic result using the {@code int} value.
     * Return {@code null} is the statistic is {@code null}.
     *
     * @param s Statistic.
     * @return the result or null
     */
    static StatisticResult getResultAsIntOrNull(StatisticResult s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the statistic result using the {@code long} value.
     * Return {@code null} is the statistic is {@code null}.
     *
     * @param s Statistic.
     * @return the result or null
     */
    static StatisticResult getResultAsLongOrNull(StatisticResult s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the statistic result using the {@code double} value.
     * Return {@code null} is the statistic is {@code null}.
     *
     * @param s Statistic.
     * @return the result or null
     */
    static StatisticResult getResultAsDoubleOrNull(StatisticResult s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the statistic result using the {@code BigInteger} value.
     * Return {@code null} is the statistic is {@code null}.
     *
     * @param s Statistic.
     * @return the result or null
     */
    static StatisticResult getResultAsBigIntegerOrNull(StatisticResult s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check left-hand side argument {@code a} is {@code null} or else the right-hand side
     * argument {@code b} must also be non-{@code null} so the statistics can be combined.
     *
     * @param <T> {@link StatisticResult} being accumulated.
     * @param a LHS.
     * @param b RHS.
     * @throws IllegalArgumentException if the objects cannot be combined
     */
    static <T extends StatisticResult & StatisticAccumulator<T>> void checkCombineCompatible(T a, T b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check left-hand side argument {@code a} is {@code null} or else the right-hand side
     * argument {@code b} must be run-time assignable to the same class as {@code a}
     * so the statistics can be combined.
     *
     * @param a LHS.
     * @param b RHS.
     * @throws IllegalArgumentException if the objects cannot be combined
     */
    static void checkCombineAssignable(FirstMoment a, FirstMoment b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If the left-hand side argument {@code a} is non-{@code null}, combine it with the
     * right-hand side argument {@code b}.
     *
     * @param <T> {@link StatisticResult} being accumulated.
     * @param a LHS.
     * @param b RHS.
     */
    static <T extends StatisticResult & StatisticAccumulator<T>> void combine(T a, T b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If the left-hand side argument {@code a} is non-{@code null}, combine it with the
     * right-hand side argument {@code b}. Assumes that the RHS is run-time assignable
     * to the same class as LHS.
     *
     * @param a LHS.
     * @param b RHS.
     * @see #checkCombineAssignable(FirstMoment, FirstMoment)
     */
    static void combineMoment(FirstMoment a, FirstMoment b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the sub-range from fromIndex (inclusive) to toIndex (exclusive) is
     * within the bounds of range from 0 (inclusive) to length (exclusive).
     *
     * <p>This function provides the functionality of
     * {@code java.utils.Objects.checkFromToIndex} introduced in JDK 9. The <a
     * href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Objects.html#checkFromToIndex(int,int,int)">Objects</a>
     * javadoc has been reproduced for reference. The return value has been changed
     * to void.
     *
     * <p>The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     * <ul>
     * <li>{@code fromIndex < 0}</li>
     * <li>{@code fromIndex > toIndex}</li>
     * <li>{@code toIndex > length}</li>
     * <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param length Upper-bound (exclusive) of the range.
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     */
    static void checkFromToIndex(int fromIndex, int toIndex, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Message formatting moved to separate methods to assist inlining of the validation methods.
    /**
     * Format a message when range [from, to) is not entirely within the length.
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param length Upper-bound (exclusive) of the range.
     * @return the message
     */
    private static String msgRangeOutOfBounds(int fromIndex, int toIndex, int length) {
        return String.format("Range [%d, %d) out of bounds for length %d", fromIndex, toIndex, length);
    }

    /**
     * Sum the specified range of {@code values}.
     *
     * <p>Warning: No range checks are performed.
     *
     * @param values Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the sum
     */
    static org.apache.commons.numbers.core.Sum sum(double[] values, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Copy the specified range of data.
     *
     * <p>This is a simplification of {@link Arrays#copyOfRange(int[], int, int)}
     * and does not support range checks or padding of the original input to
     * a longer output.
     *
     * @param data Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the copy
     */
    static int[] copy(int[] data, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Copy the specified range of data.
     *
     * <p>This is a simplification of {@link Arrays#copyOfRange(long[], int, int)}
     * and does not support range checks or padding of the original input to
     * a longer output.
     *
     * @param data Values.
     * @param from Inclusive start of the range.
     * @param to Exclusive end of the range.
     * @return the copy
     */
    static long[] copy(long[] data, int from, int to) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the statistic result using a {@code long} value.
     *
     * @param value Value.
     * @return the statistic result
     */
    static StatisticResult createStatisticResult(long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
