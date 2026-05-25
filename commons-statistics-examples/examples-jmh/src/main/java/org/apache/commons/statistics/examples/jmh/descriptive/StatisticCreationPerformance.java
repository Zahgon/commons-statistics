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
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.descriptive.DoubleStatistic;
import org.apache.commons.statistics.descriptive.GeometricMean;
import org.apache.commons.statistics.descriptive.Kurtosis;
import org.apache.commons.statistics.descriptive.Max;
import org.apache.commons.statistics.descriptive.Mean;
import org.apache.commons.statistics.descriptive.Min;
import org.apache.commons.statistics.descriptive.Product;
import org.apache.commons.statistics.descriptive.Skewness;
import org.apache.commons.statistics.descriptive.StandardDeviation;
import org.apache.commons.statistics.descriptive.Statistic;
import org.apache.commons.statistics.descriptive.Sum;
import org.apache.commons.statistics.descriptive.SumOfLogs;
import org.apache.commons.statistics.descriptive.SumOfSquares;
import org.apache.commons.statistics.descriptive.Variance;
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
 * Executes a benchmark of the creation of a statistic from {@code double} array data.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class StatisticCreationPerformance {

    /**
     * Source of {@code double} array data.
     */
    @State(Scope.Benchmark)
    public static class DataSource {

        /**
         * Data length.
         */
        @Param({ "0", "1", "10", "100", "1000", "10000" })
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
         * @return the start inclusive of the sub-range.
         */
        public int from() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the end exclusive of the sub-range.
         */
        public int to() {
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
     * Function for creating an object from a range of values.
     *
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     */
    @FunctionalInterface
    interface RangeFunction<T, R> {

        /**
         * Returns an object created using the specified range of {@code values}.
         *
         * @param values Values.
         * @param from Inclusive start of the range.
         * @param to Exclusive end of the range.
         * @return result
         */
        R apply(T values, int from, int to);
    }

    /**
     * Source of a {@code Statistic}.
     */
    @State(Scope.Benchmark)
    public static class StatisticSource {

        /**
         * The statistic to create.
         */
        @Param()
        private Statistic statistic;

        /**
         * Statistic factory.
         */
        private Supplier<DoubleStatistic> supplier;

        /**
         * Statistic factory using input data.
         */
        private Function<double[], DoubleStatistic> factory;

        /**
         * Statistic factory using a range of input data.
         */
        private RangeFunction<double[], DoubleStatistic> rangeFactory;

        /**
         * @return a statistic instance
         */
        public DoubleStatistic create() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @param x Values.
         * @return a statistic instance
         */
        public DoubleStatistic create(double[] x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @param x Values.
         * @param from Inclusive start of the range.
         * @param to Exclusive end of the range.
         * @return a statistic instance
         */
        public DoubleStatistic create(double[] x, int from, int to) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the factory functions.
         */
        @Setup(Level.Trial)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@code Statistic} created using a custom implementation.
     * This contains alternative version of creating statistics from an array
     * for benchmarking performance.
     */
    @State(Scope.Benchmark)
    public static class CustomStatisticSource {

        /**
         * The statistic to create.
         */
        @Param({ "min", "product" })
        private String statistic;

        /**
         * Statistic factory using input data.
         */
        private Function<double[], DoubleSupplier> factory;

        /**
         * Statistic factory using a range of input data.
         */
        private RangeFunction<double[], DoubleSupplier> rangeFactory;

        /**
         * @param x Values.
         * @return a statistic instance
         */
        public DoubleSupplier create(double[] x) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @param x Values.
         * @param from Inclusive start of the range.
         * @param to Exclusive end of the range.
         * @return a statistic instance
         */
        public DoubleSupplier create(double[] x, int from, int to) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the factory functions.
         */
        @Setup(Level.Trial)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the minimum.
         */
        static final class CMin implements DoubleSupplier {

            /**
             * Current statistic.
             */
            private double s;

            /**
             * Create an instance.
             * @param s Statistic value.
             */
            private CMin(double s) {
                this.s = s;
            }

            /**
             * @param values Values.
             * @return instance.
             */
            static CMin of(double... values) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            /**
             * @param values Values.
             * @param from Inclusive start of the range.
             * @param to Exclusive end of the range.
             * @return instance.
             */
            static CMin ofRange(double[] values, int from, int to) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double getAsDouble() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * Compute the product.
         */
        static final class CProduct implements DoubleSupplier {

            /**
             * Current statistic.
             */
            private double s;

            /**
             * Create an instance.
             * @param s Statistic value.
             */
            private CProduct(double s) {
                this.s = s;
            }

            /**
             * @param values Values.
             * @return instance.
             */
            static CProduct of(double... values) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            /**
             * @param values Values.
             * @param from Inclusive start of the range.
             * @param to Exclusive end of the range.
             * @return instance.
             */
            static CProduct ofRange(double[] values, int from, int to) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double getAsDouble() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }
    }

    /**
     * Create the statistic using an array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double array(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using an array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double customArray(DataSource dataSource, CustomStatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a for loop.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double forLoop(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a for-each loop.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double forEachLoop(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a stream.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double streamForEach(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a range of the array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double arrayRange(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a range of the array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double customArrayRange(DataSource dataSource, CustomStatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a copy of a range of the array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double arrayCopyOfRange(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a copy of a range of the array.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double customArrayCopyOfRange(DataSource dataSource, CustomStatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a for loop on a range of the data.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double forLoopRange(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a for-each loop on a copy of a range of the data.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double forEachLoopCopyOfRange(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a stream on a range of the data.
     *
     * @param dataSource Source of the data.
     * @param statisticSource Source of the statistic.
     * @return the statistic
     */
    @Benchmark
    public double streamForEachRange(DataSource dataSource, StatisticSource statisticSource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
