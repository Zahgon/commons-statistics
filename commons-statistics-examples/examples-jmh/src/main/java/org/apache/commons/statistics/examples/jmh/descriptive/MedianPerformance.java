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
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import org.apache.commons.statistics.descriptive.Median;
import org.apache.commons.statistics.examples.jmh.descriptive.QuantilePerformance.AbstractDataSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Executes a benchmark of the creation of a median from array data.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx8192M" })
public class MedianPerformance {

    /**
     * Use the JDK sort function.
     */
    private static final String JDK = "JDK";

    /**
     * Commons Math 3 Percentile implementation.
     */
    private static final String CM3 = "CM3";

    /**
     * Commons Math 4 Percentile implementation.
     */
    private static final String CM4 = "CM4";

    /**
     * Commons Statistics implementation.
     */
    private static final String STATISTICS = "Statistics";

    /**
     * Source of {@code double} array data.
     *
     * <p>This uses the same data class as {@link QuantilePerformance}.
     * This enables reuse of the various data distributions provided.
     */
    @State(Scope.Benchmark)
    public static class DataSource extends AbstractDataSource {

        /**
         * Data length.
         */
        @Param({ "1000", "100000" })
        private int length;

        /**
         * {@inheritDoc}
         */
        @Override
        protected int getLength() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link ToDoubleFunction} for a {@code double[]}.
     */
    @State(Scope.Benchmark)
    public static class DoubleFunctionSource {

        /**
         * Name of the source.
         *
         * <p>Note: CM3 and CM4 are not run by default. The default data source
         * uses the Bentley and McIlroy data which includes data that is
         * very slow using a single-pivot quickselect method with a median-of-3
         * pivot strategy. This is the method used by Commons Math and each
         * benchmark iteration may take orders of magnitude longer than the target
         * measurement time of 1 second.
         */
        @Param({ JDK, STATISTICS })
        private String name;

        /**
         * The action.
         */
        private ToDoubleFunction<double[]> function;

        /**
         * @return the function
         */
        public ToDoubleFunction<double[]> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sort the values and compute the median.
         *
         * @param values Values.
         * @return the median
         */
        private static double sortMedian(double[] values) {
            // Implicit NPE
            final int n = values.length;
            // Special cases
            if (n <= 2) {
                switch(n) {
                    case 2:
                        return (values[0] + values[1]) * 0.5;
                    case 1:
                        return values[0];
                    default:
                        return Double.NaN;
                }
            }
            // A sort is required
            Arrays.sort(values);
            final int k = n >>> 1;
            // Odd
            if ((n & 0x1) == 0x1) {
                return values[k];
            }
            // Even
            return (values[k - 1] + values[k]) * 0.5;
        }
    }

    /**
     * Source of a {@link ToDoubleFunction} for a {@code int[]}.
     */
    @State(Scope.Benchmark)
    public static class IntFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ JDK, STATISTICS })
        private String name;

        /**
         * The action.
         */
        private ToDoubleFunction<int[]> function;

        /**
         * @return the function
         */
        public ToDoubleFunction<int[]> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sort the values and compute the median.
         *
         * @param values Values.
         * @return the median
         */
        private static double sortMedian(int[] values) {
            // Implicit NPE
            final int n = values.length;
            // Special cases
            if (n <= 2) {
                switch(n) {
                    case 2:
                        return (values[0] + values[1]) * 0.5;
                    case 1:
                        return values[0];
                    default:
                        return Double.NaN;
                }
            }
            // A sort is required
            Arrays.sort(values);
            final int k = n >>> 1;
            // Odd
            if ((n & 0x1) == 0x1) {
                return values[k];
            }
            // Even
            return (values[k - 1] + values[k]) * 0.5;
        }
    }

    /**
     * Source of a {@link Function} for a {@code long[]}.
     */
    @State(Scope.Benchmark)
    public static class LongFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ JDK, STATISTICS })
        private String name;

        /**
         * The action.
         */
        private Function<long[], Object> function;

        /**
         * @return the function
         */
        public Function<long[], Object> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sort the values and compute the median.
         *
         * @param values Values.
         * @return the median
         */
        private static Double sortMedian(long[] values) {
            // Implicit NPE
            final int n = values.length;
            // Special cases
            if (n <= 2) {
                switch(n) {
                    case 2:
                        return (values[0] + values[1]) * 0.5;
                    case 1:
                        return (double) values[0];
                    default:
                        return Double.NaN;
                }
            }
            // A sort is required
            Arrays.sort(values);
            final int k = n >>> 1;
            // Odd
            if ((n & 0x1) == 0x1) {
                return (double) values[k];
            }
            // Even
            return (values[k - 1] + values[k]) * 0.5;
        }
    }

    /**
     * Create the statistic using an array.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @param bh Data sink.
     */
    @Benchmark
    public void doubleMedian(DoubleFunctionSource function, DataSource source, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using an array.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @param bh Data sink.
     */
    @Benchmark
    public void intMedian(IntFunctionSource function, DataSource source, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using an array.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @param bh Data sink.
     */
    @Benchmark
    public void longMedian(LongFunctionSource function, DataSource source, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
