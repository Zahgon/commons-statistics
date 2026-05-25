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

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.numbers.core.DD;
import org.apache.commons.numbers.core.Sum;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.descriptive.Mean;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Executes a benchmark of the moment-based statistics.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class MomentPerformance {

    /**
     * Commons Statistics Mean implementation.
     */
    private static final String MEAN = "Mean";

    /**
     * Summation mean implementation.
     */
    private static final String SUM_MEAN = "SumMean";

    /**
     * Extended precision summation mean implementation.
     */
    private static final String EXTENDED_SUM_MEAN = "ExtendedSumMean";

    /**
     * Extended precision summation (using Numbers Sum).
     */
    private static final String NUMBERS_SUM = "NumbersSum";

    /**
     * Extended precision summation (using Numbers Sum) with computation of non-finite value.
     */
    private static final String NUMBERS_SUM2 = "NumbersSum2";

    /**
     * Rolling mean implementation.
     */
    private static final String ROLLING_MEAN = "RollingMean";

    /**
     * Safe rolling mean implementation.
     */
    private static final String SAFE_ROLLING_MEAN = "SafeRollingMean";

    /**
     * Safe rolling mean implementation.
     */
    private static final String SCALED_ROLLING_MEAN = "ScaledRollingMean";

    /**
     * Safe rolling mean implementation with computation of non-finite value.
     */
    private static final String SCALED_ROLLING_MEAN2 = "ScaledRollingMean2";

    /**
     * Safe rolling mean implementation with computation of non-finite value.
     */
    private static final String SCALED_ROLLING_MEAN3 = "ScaledRollingMean3";

    /**
     * Inline rolling mean implementation for array-based creation.
     */
    private static final String INLINE_ROLLING_MEAN = "InlineRollingMean";

    /**
     * Inline safe rolling mean implementation for array-based creation.
     */
    private static final String INLINE_SAFE_ROLLING_MEAN = "InlineSafeRollingMean";

    /**
     * Inline safe rolling mean implementation with extended precision for array-based creation.
     */
    private static final String INLINE_SAFE_ROLLING_MEAN_EXT = "InlineSafeRollingMeanExt";

    /**
     * Source of {@code double} array data.
     */
    @State(Scope.Benchmark)
    public static class DataSource {

        /**
         * Data length.
         */
        @Param({ "1", "10", "1000" })
        private int length;

        /**
         * Data.
         */
        private double[] data;

        /**
         * @return the data
         */
        public double[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link DoubleConsumer} action.
     */
    @State(Scope.Benchmark)
    public static class ActionSource {

        /**
         * Name of the source.
         */
        @Param({ MEAN, ROLLING_MEAN, SAFE_ROLLING_MEAN, SCALED_ROLLING_MEAN, SUM_MEAN, EXTENDED_SUM_MEAN, SCALED_ROLLING_MEAN2, SCALED_ROLLING_MEAN3, NUMBERS_SUM, NUMBERS_SUM2 })
        private String name;

        /**
         * The action.
         */
        private Supplier<DoubleConsumer> action;

        /**
         * @return the action
         */
        public DoubleConsumer getAction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link Function} for a {@code double[]}.
     */
    @State(Scope.Benchmark)
    public static class FunctionSource {

        /**
         * Name of the source.
         */
        @Param({ MEAN, ROLLING_MEAN, SAFE_ROLLING_MEAN, SCALED_ROLLING_MEAN, INLINE_SAFE_ROLLING_MEAN, INLINE_SAFE_ROLLING_MEAN_EXT, SUM_MEAN, EXTENDED_SUM_MEAN, "DDMean", // Same speed as the ROLLING_MEAN, i.e. the DoubleConsumer is not an overhead
        //INLINE_ROLLING_MEAN
        // Higher moments
        "SumOfCubed", "SumOfCubedPow", "SumOfFourth", "SumOfFourthPow" })
        private String name;

        /**
         * The action.
         */
        private Function<double[], Object> function;

        /**
         * @return the function
         */
        public Function<double[], Object> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A rolling first raw moment of {@code double} data.
     */
    static class RollingFirstMoment implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * First moment of values that have been added.
         */
        private double m1;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A rolling first raw moment of {@code double} data safe to overflow of any finite
     * values (e.g. [MAX_VALUE, -MAX_VALUE]).
     */
    static class SafeRollingFirstMoment implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * First moment of values that have been added.
         */
        private double m1;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A rolling first raw moment of {@code double} data safe to overflow of any finite
     * values (e.g. [MAX_VALUE, -MAX_VALUE]).
     */
    static class ScaledRollingFirstMoment implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * First moment of values that have been added.
         */
        private double m1;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A rolling first raw moment of {@code double} data safe to overflow of any finite
     * values (e.g. [MAX_VALUE, -MAX_VALUE]). This includes computation of the correct
     * non-finite value.
     */
    static class ScaledRollingFirstMoment2 implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * First moment of values that have been added.
         */
        private double m1;

        /**
         * Non-finite result. This is the sum of non-finite values.
         */
        private double nonFiniteValue;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A rolling first raw moment of {@code double} data safe to overflow of any finite
     * values (e.g. [MAX_VALUE, -MAX_VALUE]). This includes computation of the correct
     * non-finite value.
     */
    static class ScaledRollingFirstMoment3 implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * First moment of values that have been added.
         */
        private double m1;

        /**
         * Non-finite result. This is the sum of non-finite values.
         */
        private double nonFiniteValue;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A mean using a sum.
     */
    static class SumFirstMoment implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * Sum of values that have been added.
         */
        private double sum;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A mean using an extended precision sum.
     *
     * <p>This type of summation is used in DoubleStream to compute the sum and derive the
     * mean. This method acts as a proxy to compare the speed of the rolling algorithm to
     * collect a stream verses a high-precision sum using
     * {@link java.util.stream.DoubleStream#sum()}.
     */
    static class ExtendedSumFirstMoment implements DoubleConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * Sum of values that have been added.
         */
        private double sum;

        /**
         * A running compensation for lost low-order bits.
         */
        private double c;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A sum using an Commons Numbers {@link Sum}.
     */
    static class NumbersSum implements DoubleConsumer, DoubleSupplier {

        /**
         * Sum of values that have been added.
         */
        private final Sum sum = Sum.create();

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A sum using an Commons Numbers {@link Sum} with computation of the correct
     * non-finite value.
     */
    static class NumbersSum2 implements DoubleConsumer, DoubleSupplier {

        /**
         * Sum of values that have been added.
         */
        private final Sum sum = Sum.create();

        /**
         * Non-finite result. This is the sum of non-finite values.
         */
        private double nonFiniteValue;

        @Override
        public void accept(double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Apply the action to each value.
     *
     * @param <T> the action type
     * @param action Action.
     * @param values Values.
     * @return the action
     */
    static <T extends DoubleConsumer> T forEach(T action, double[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Correct the mean using a second pass over the data.
     *
     * @param data Data.
     * @param xbar Current mean.
     * @return the mean
     */
    private static double correctMean(double[] data, double xbar) {
        double correction = 0;
        for (final double x : data) {
            correction += x - xbar;
        }
        // Note: Correction may be infinite
        if (Double.isFinite(correction)) {
            return xbar + correction / data.length;
        }
        return xbar;
    }

    /**
     * Correct the mean using a second pass over the data.
     *
     * @param data Data.
     * @param xbar Current mean.
     * @return the mean
     */
    private static double correctMeanKahan(double[] data, double xbar) {
        // Second pass (Kahan summation)
        double correction = 0;
        double c = 0;
        for (final double x : data) {
            final double dx = x - xbar;
            final double y = dx - c;
            final double t = correction + y;
            c = (t - correction) - y;
            correction = t;
        }
        // Note: Correction may be infinite
        if (Double.isFinite(correction)) {
            return xbar + correction / data.length;
        }
        return xbar;
    }

    /**
     * Create the two-pass mean using a rolling first moment.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayRollingFirstMoment(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the two-pass mean using a rolling first moment
     * safe to overflow.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySafeRollingFirstMoment(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the two-pass mean using a rolling first moment
     * safe to overflow.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayScaledRollingFirstMoment(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the two-pass mean using a rolling first moment inline.
     *
     * <p>Note: This method is effectively the same as {@link #arrayRollingFirstMoment(double[])}
     * and timing tests show there is no overhead to using an object to aggregate the first moment,
     * i.e. this is not faster.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayInlineRollingFirstMoment(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the two-pass mean using a rolling first moment inline.
     * The result is safe for all finite input by using downscaling.
     * Upscaling is applied to the end result.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayInlineSafeRollingFirstMoment(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the two-pass mean using a rolling first moment inline.
     * The result is safe for all finite input by using downscaling.
     * Upscaling is applied to the end result.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayInlineSafeRollingFirstMomentExt(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the mean using a single pass sum.
     * The mean is not safe against overflow.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySumMean(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create themean using a single pass sum using double-double precision.
     * The mean is not safe against overflow or non-finite input.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arrayDDSumMean(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the sum-of-cubed deviations from the mean.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySumOfCubed(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the sum-of-cubed deviations from the mean using the
     * {@link Math#pow(double, double)} function.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySumOfCubedPow(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the sum-of-fourth deviations from the mean.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySumOfFourth(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the sum-of-fourth deviations from the mean using the
     * {@link Math#pow(double, double)} function.
     *
     * @param data Data.
     * @return the statistic
     */
    static double arraySumOfFourthPow(double[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the mean from a stream of {@code double} values.
     *
     * @param source Source of the data.
     * @return the mean
     */
    @Benchmark
    public Object streamMean(DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a consumer of {@code double} values.
     *
     * @param action Source of the data action.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public Object forEachStatistic(ActionSource action, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a {@code double[]} function.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public Object arrayStatistic(FunctionSource function, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
