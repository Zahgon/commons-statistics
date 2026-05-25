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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.LongStream;
import org.apache.commons.numbers.core.DD;
import org.apache.commons.numbers.fraction.BigFraction;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.descriptive.DoubleStatistic;
import org.apache.commons.statistics.descriptive.IntMean;
import org.apache.commons.statistics.descriptive.IntStatistic;
import org.apache.commons.statistics.descriptive.IntVariance;
import org.apache.commons.statistics.descriptive.Kurtosis;
import org.apache.commons.statistics.descriptive.LongMean;
import org.apache.commons.statistics.descriptive.LongStatistic;
import org.apache.commons.statistics.descriptive.LongVariance;
import org.apache.commons.statistics.descriptive.Mean;
import org.apache.commons.statistics.descriptive.Skewness;
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
import org.openjdk.jmh.infra.Blackhole;

/**
 * Executes a benchmark of the moment-based statistics for integer values
 * ({@code int} or {@code long}) compared to using {@code double} values.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class IntMomentPerformance {

    /**
     * Commons Statistics Mean implementation.
     */
    private static final String DOUBLE_MEAN = "DoubleMean";

    /**
     * Integer mean implementation.
     */
    private static final String INT_MEAN = "IntMean";

    /**
     * Long mean implementation.
     */
    private static final String LONG_MEAN = "LongMean";

    /**
     * Sum using a long mean implementation.
     */
    private static final String LONG_SUM_MEAN = "LongSumMean";

    /**
     * Sum using a BigInteger mean implementation.
     */
    private static final String BIG_INTEGER_SUM_MEAN = "BigIntegerSumMean";

    /**
     * JDK Stream mean implementation.
     */
    private static final String STREAM_MEAN = "StreamMean";

    /**
     * Commons Statistics Variance implementation.
     */
    private static final String DOUBLE_VAR = "DoubleVariance";

    /**
     * Integer variance implementation.
     */
    private static final String INT_VAR = "IntVariance";

    /**
     * Long variance implementation.
     */
    private static final String LONG_VAR = "LongVariance";

    /**
     * Long variance implementation using Math.multiplyHigh.
     */
    private static final String LONG_VAR2 = "LongVariance2";

    /**
     * Commons Statistics Skewness implementation.
     */
    private static final String DOUBLE_SKEWNESS = "DoubleSkewness";

    /**
     * Commons Statistics Kurtosis implementation.
     */
    private static final String DOUBLE_KURTOSIS = "DoubleKurtosis";

    /**
     * Int specialization for skewness.
     */
    private static final String INT_SKEWNESS = "IntSkewness";

    /**
     * Source of array data.
     */
    @State(Scope.Benchmark)
    public static class DataSource {

        /**
         * Data length.
         */
        @Param({ "2", "1000" })
        private int length;

        /**
         * Data.
         */
        private int[] data;

        /**
         * Data as a double.
         */
        private double[] doubleData;

        /**
         * Data as a long.
         */
        private long[] longData;

        /**
         * @return the data
         */
        public int[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the data
         */
        public double[] getDoubleData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the data
         */
        public long[] getLongData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         * Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link IntConsumer} action.
     */
    @State(Scope.Benchmark)
    public static class IntActionSource {

        /**
         * Name of the source.
         */
        @Param({ DOUBLE_MEAN, INT_MEAN, // Disabled: Run-time ~ IntMean
        // LONG_SUM_MEAN
        DOUBLE_VAR, INT_VAR, DOUBLE_SKEWNESS, DOUBLE_KURTOSIS, INT_SKEWNESS })
        private String name;

        /**
         * The action.
         */
        private Supplier<IntStatistic> action;

        /**
         * @return the action
         */
        public IntStatistic getAction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates the {@link IntStatistic}.
         *
         * @param c Consumer.
         * @param s Supplier.
         * @return the statistic
         */
        private static IntStatistic createIntStatistic(IntConsumer c, DoubleSupplier s) {
            return new IntStatistic() {

                @Override
                public void accept(int value) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public double getAsDouble() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }

        /**
         * Creates the {@link IntStatistic}.
         *
         * @param c Consumer.
         * @param s Supplier.
         * @return the statistic
         */
        private static IntStatistic createDoubleAsIntStatistic(DoubleConsumer c, DoubleSupplier s) {
            return new IntStatistic() {

                @Override
                public void accept(int value) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public double getAsDouble() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }
    }

    /**
     * Source of a {@link DoubleConsumer} action.
     */
    @State(Scope.Benchmark)
    public static class DoubleActionSource {

        /**
         * Name of the source.
         */
        @Param({ DOUBLE_MEAN, DOUBLE_VAR, DOUBLE_SKEWNESS, DOUBLE_KURTOSIS })
        private String name;

        /**
         * The action.
         */
        private Supplier<DoubleStatistic> action;

        /**
         * @return the action
         */
        public DoubleStatistic getAction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates the {@link DoubleStatistic}.
         *
         * <p>This method is here to provide parity when comparing actual instances
         * of {@link DoubleStatistic} with composed objects for the equivalent
         * int/long statistics.
         *
         * @param c Consumer.
         * @param s Supplier.
         * @return the statistic
         */
        private static DoubleStatistic createDoubleStatistic(DoubleConsumer c, DoubleSupplier s) {
            return new DoubleStatistic() {

                @Override
                public void accept(double value) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public double getAsDouble() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }
    }

    /**
     * Source of a {@link LongConsumer} action.
     */
    @State(Scope.Benchmark)
    public static class LongActionSource {

        /**
         * Name of the source.
         */
        @Param({ DOUBLE_MEAN, LONG_MEAN, BIG_INTEGER_SUM_MEAN, DOUBLE_VAR, LONG_VAR, LONG_VAR2, DOUBLE_SKEWNESS, DOUBLE_KURTOSIS })
        private String name;

        /**
         * The action.
         */
        private Supplier<LongStatistic> action;

        /**
         * @return the action
         */
        public LongStatistic getAction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates the {@link LongStatistic}.
         *
         * @param c Consumer.
         * @param s Supplier.
         * @return the statistic
         */
        private static LongStatistic createLongStatistic(LongConsumer c, DoubleSupplier s) {
            return new LongStatistic() {

                @Override
                public void accept(long value) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public double getAsDouble() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }

        /**
         * Creates the {@link LongStatistic}.
         *
         * @param c Consumer.
         * @param s Supplier.
         * @return the statistic
         */
        private static LongStatistic createDoubleAsLongStatistic(DoubleConsumer c, DoubleSupplier s) {
            return new LongStatistic() {

                @Override
                public void accept(long value) {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public double getAsDouble() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
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
        @Param({ INT_MEAN, // Disabled: Run-time ~ IntMean
        //LONG_SUM_MEAN,
        STREAM_MEAN, INT_VAR, INT_SKEWNESS })
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
        @Setup(Level.Iteration)
        public void setup() {
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
         */
        @Param({ DOUBLE_MEAN, DOUBLE_VAR, DOUBLE_SKEWNESS, DOUBLE_KURTOSIS })
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
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link ToDoubleFunction} for a {@code long[]}.
     */
    @State(Scope.Benchmark)
    public static class LongFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ LONG_MEAN, BIG_INTEGER_SUM_MEAN, LONG_VAR, LONG_VAR2 })
        private String name;

        /**
         * The action.
         */
        private ToDoubleFunction<long[]> function;

        /**
         * @return the function
         */
        public ToDoubleFunction<long[]> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Class containing the variance data.
     */
    static class IntVarianceData {

        /**
         * Sum of the squared values.
         */
        private final UInt128 sumSq;

        /**
         * Sum of the values.
         */
        private final Int128 sum;

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * @param sumSq Sum of the squared values.
         * @param sum Sum of the values.
         * @param n Count of values that have been added.
         */
        IntVarianceData(UInt128 sumSq, Int128 sum, long n) {
            this.sumSq = sumSq;
            this.sum = sum;
            this.n = n;
        }

        /**
         * @return the sum of the squared values
         */
        UInt128 getSumSq() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the sum
         */
        Int128 getSum() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the count of values that have been added
         */
        long getN() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the copy
         */
        IntVarianceData copy() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Adds the other instance.
         *
         * @param other the other
         * @return this instance
         */
        IntVarianceData add(IntVarianceData other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of {@code int} variance data.
     *
     * <p>This class generates a pool of variance data from a random sample of integers
     * in a range. The pool objects are then combined with each other for a given number of
     * rounds, effectively doubling the size of pool objects each round.
     * Using the defaults will create objects in the pool of:
     * <pre>
     * E[ sum(x) ] = (511 / 2) mean value * (95 / 2) mean samples ~ 12136.25 ~ 2^8 * 2^5.5 {@code < 2^14}
     * E[ sum(x^2) = ((511 / 2)^2 mean value^2) * (95 / 2) mean samples ~ 3100811.875 ~ 2^16 * 2^5.5 {@code < 2^22}
     * Max[ sum(x) = 511 * 63 = 32193 {@code < 2^15}
     * Max[ sum(x^2) = 15^2 * 63 = 14175 {@code < 2^16}
     * man[ n ] = 63 {@code < 2^6}
     * </pre>
     * <p>The objects from this pool can be added together a maximum of 56 times before n overflows.
     * The sum of the values will overflow a long at approximately 18 combines.
     */
    @State(Scope.Benchmark)
    public static class IntVarianceDataSource {

        /**
         * Consistent seed.
         */
        private static final Long SEED = ThreadLocalRandom.current().nextLong();

        /**
         * Lower limit.
         */
        @Param({ "0" })
        private int origin;

        /**
         * Upper limit.
         */
        @Param({ "512" })
        private int bound;

        /**
         * Minimum samples.
         */
        @Param({ "32" })
        private int minSamples;

        /**
         * Maximum samples.
         */
        @Param({ "64" })
        private int maxSamples;

        /**
         * Pool size.
         */
        @Param({ "64" })
        private int poolSize;

        /**
         * Number of combine operations.
         */
        @Param({ "8", "16", "24", "32", "48" })
        private int combine;

        /**
         * Data.
         */
        private IntVarianceData[] data;

        /**
         * The number of data values.
         *
         * @return the size
         */
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get a copy of the data for the specified index.
         *
         * @param i Index.
         * @return the data
         */
        public IntVarianceData getData(int i) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link ToDoubleFunction} for a {@code IntVarianceData}.
     */
    @State(Scope.Benchmark)
    public static class IntVarianceFunctionSource {

        /**
         * {@link MathContext} with 20 digits of precision.
         */
        private static final MathContext MC_20_DIGITS = new MathContext(20);

        /**
         * Name of the source.
         */
        @Param({ "DD", "DD2", "BigIntegerPow", "BigIntegerMultiply", "SumSquareBigInteger", "SumSquareMultiplyBigInteger", "UIntBigInteger", "UIntDD", "UIntDD2", "UIntBigInteger2", "UIntBigInteger3", "UIntDouble" // Very slow
        //"UIntBigFraction", "UIntBigDecimal"
        })
        private String name;

        /**
         * The action.
         */
        private ToDoubleFunction<IntVarianceData> function;

        /**
         * @return the function
         */
        public ToDoubleFunction<IntVarianceData> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Convenience method to square a BigInteger.
         *
         * @param x Value
         * @return x^2
         */
        private static BigInteger square(BigInteger x) {
            return x.multiply(x);
        }

        /**
         * Compute the variance using double-double arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceDD(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using double-double arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceDD2(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using BigInteger arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceBigIntegerPow(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using BigInteger arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceBigIntegerMultiply(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using Int128 and BigInteger arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceSumSquareBigInteger(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using UInt128/Int128 and BigInteger arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceSumSquareMultiplyIntBigInteger(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using UInt128/Int128 and BigInteger arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntBigInteger(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using UInt128/Int128 and DD arithmetic.
         * The final divide uses double precision.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntDD(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using UInt128/Int128 and DD arithmetic.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntDD2(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using unsigned integer (UInt128/Int128 or BigInteger) arithmetic.
         * The final divide uses double precision.
         *
         * <p>Note: This is similar to {@link #varianceUIntBigInteger(IntVarianceData)} but does
         * not fast compute the squared sum. This benchmarks as faster: the BigInteger multiply
         * on small values for sum(x)^2 is efficient.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntBigInteger2(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using unsigned integer (UInt128/Int128 or BigInteger) arithmetic.
         * The final divide uses double precision.
         *
         * <p>Note: This is similar to {@link #varianceUIntBigInteger(IntVarianceData)} but does
         * computes the squared sum in Int128. This benchmarks slower than converting to BigInteger
         * and computing the square.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntBigInteger3(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using unsigned integer (UInt128/Int128 or BigInteger) arithmetic.
         * The final divide uses double precision.
         *
         * <p>Note: This is similar to {@link #varianceUIntBigInteger(IntVarianceData)} but does
         * not fast compute the squared sum. This benchmarks as faster: the BigInteger multiply
         * on small values for sum(x)^2 is efficient.
         *
         * <p>This method uses the {@link UInt128#toDouble()} to avoid going via BigInteger.
         * The divisor is computed in extended precision.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntDouble(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using unsigned integer (UInt128/Int128 or BigInteger) arithmetic.
         * The final divide uses double precision.
         *
         * <p>Note: This is similar to {@link #varianceUIntBigInteger(IntVarianceData)} but does
         * not fast compute the squared sum. This benchmarks as faster: the BigInteger multiply
         * on small values for sum(x)^2 is efficient.
         *
         * <p>The final divide uses BigFraction for large size, or double.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntBigFraction(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the variance using unsigned integer (UInt128/Int128 or BigInteger) arithmetic.
         * The final divide uses double precision.
         *
         * <p>Note: This is similar to {@link #varianceUIntBigInteger(IntVarianceData)} but does
         * not fast compute the squared sum. This benchmarks as faster: the BigInteger multiply
         * on small values for sum(x)^2 is efficient.
         *
         * <p>The final divide uses BigDecimal for large size, or double.
         *
         * @param data Variance data.
         * @return the variance
         */
        static double varianceUIntBigDecimal(IntVarianceData data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of {@code long} array data.
     * The data is designed to overflow a sum as a long with a specified frequency.
     * There are 3 cases: positive values; negative values; any sign. The amount
     * of overflow is controlled using a shift to remove magnitude. No shift expects
     * overflow 50% of the time when summing same sign values. If both signs are used then the
     * random walk will be based around 0 with overflow occurring proportional to
     * the magnitude. Chance of overflow will rapidly drop when the values are not full
     * magnitude numbers.
     */
    @State(Scope.Benchmark)
    public static class LongDataSource {

        /**
         * Data length: 2^10. If shift is above 10 then no overflow will occur.
         */
        @Param({ "1024" })
        private int length;

        /**
         * Data sign.
         */
        @Param({ "positive", "negative", "both" })
        private String sign;

        /**
         * Data bit shift.
         */
        @Param({ "0", "1", "2", "4", "8", "16" })
        private int shift;

        /**
         * Data.
         */
        private long[] data;

        /**
         * @return the data
         */
        public long[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         * Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link ToLongFunction} for a {@code long[]}.
     */
    @State(Scope.Benchmark)
    public static class LongSumFunctionSource {

        /**
         * Name of the source.
         * The branchless 128bitAdd2 runs at constant speed but is slower than 128bitAdd.
         */
        @Param({ "128bitAdd", "128bitAdd2", "64bitSum" })
        private String name;

        /**
         * The action.
         */
        private ToLongFunction<long[]> function;

        /**
         * @return the function
         */
        public ToLongFunction<long[]> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of {@code long} array data to multiply as unsigned pairs.
     * Magnitude is approximately controlled using a bit shift on the values.
     */
    @State(Scope.Benchmark)
    public static class MultiplyLongDataSource {

        /**
         * Data length.
         */
        @Param({ "1024" })
        private int length;

        /**
         * Data bit shift.
         */
        @Param({ "0", "33" })
        private int shift;

        /**
         * Data.
         */
        private long[] data;

        /**
         * @return the data
         */
        public long[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         * Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link ToDoubleFunction} for a {@code long[]}.
     */
    @State(Scope.Benchmark)
    public static class MultiplyLongFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ "double", "unsignedMultiplyToDoubleBigInteger", "unsignedMultiplyToDouble" })
        private String name;

        /**
         * The action.
         */
        private ToDoubleFunction<long[]> function;

        /**
         * Function for two long arguments.
         */
        interface LongLongToDoubleFunction {

            /**
             * Apply the function.
             *
             * @param a Value.
             * @param b Value.
             * @return the result
             */
            double apply(long a, long b);
        }

        /**
         * @return the function
         */
        public ToDoubleFunction<long[]> getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates the function.
         *
         * @param functionName Function name.
         * @return the function
         */
        private LongLongToDoubleFunction createFunction(String functionName) {
            if ("double".equals(functionName)) {
                return (x, y) -> (double) x * y;
            } else if ("unsignedMultiplyToDoubleBigInteger".equals(name)) {
                return IntMath::unsignedMultiplyToDoubleBigInteger;
            } else if ("unsignedMultiplyToDouble".equals(name)) {
                return IntMath::unsignedMultiplyToDouble;
            } else {
                throw new IllegalStateException("Unknown multiply long function: " + name);
            }
        }

        /**
         * Apply the function to all pairs in the data.
         *
         * @param array Data.
         * @param f Function.
         * @return the result
         */
        private static double applyAll(long[] array, LongLongToDoubleFunction f) {
            double s = 0;
            for (int i = 0; i < array.length; i += 2) {
                s += f.apply(array[i], array[i + 1]);
            }
            return s;
        }
    }

    /**
     * A mean of {@code int} data using a {@code long} sum.
     */
    static class LongSumMean implements IntConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * Sum of values that have been added.
         */
        private long s;

        @Override
        public void accept(int value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the mean using a sum.
         *
         * @param data Data.
         * @return the mean
         */
        static double mean(int[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A mean of {@code long} data using a {@code BigInteger} sum.
     */
    static class BigIntegerSumMean implements LongConsumer, DoubleSupplier {

        /**
         * Count of values that have been added.
         */
        private long n;

        /**
         * Sum of values that have been added.
         */
        private BigInteger s = BigInteger.ZERO;

        @Override
        public void accept(long value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getAsDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the mean using a sum.
         *
         * @param data Data.
         * @return the mean
         */
        static double mean(long[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Apply the action to each {@code int} value.
     *
     * @param <T> the action type
     * @param action Action.
     * @param values Values.
     * @return the value
     */
    static <T extends IntConsumer & DoubleSupplier> double forEach(T action, int[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Apply the action to each {@code double} value.
     *
     * @param <T> the action type
     * @param action Action.
     * @param values Values.
     * @return the value
     */
    static <T extends DoubleConsumer & DoubleSupplier> double forEach(T action, double[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Apply the action to each {@code long} value.
     *
     * @param <T> the action type
     * @param action Action.
     * @param values Values.
     * @return the value
     */
    static <T extends LongConsumer & DoubleSupplier> double forEach(T action, long[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a consumer of {@code int} values.
     *
     * @param action Source of the data action.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public double forEachIntStatistic(IntActionSource action, DataSource source) {
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
    public double forEachDoubleStatistic(DoubleActionSource action, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a consumer of {@code long} values.
     *
     * @param action Source of the data action.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public double forEachLongStatistic(LongActionSource action, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a {@code int[]} function.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public double arrayIntStatistic(IntFunctionSource function, DataSource source) {
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
    public double arrayDoubleStatistic(DoubleFunctionSource function, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the statistic using a {@code long[]} function.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @return the statistic
     */
    @Benchmark
    public double arrayLongStatistic(LongFunctionSource function, DataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the variance using a aggregated {@code int[]} data.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @param bh Data sink.
     */
    @Benchmark
    public void intVariance(IntVarianceFunctionSource function, IntVarianceDataSource source, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the sum using a {@code long[]} function.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @return the sum
     */
    @Benchmark
    public long longSum(LongSumFunctionSource function, LongDataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the product using a {@code long[]} function.
     *
     * @param function Source of the function.
     * @param source Source of the data.
     * @return the sum
     */
    @Benchmark
    public double multiplyToDouble(MultiplyLongFunctionSource function, MultiplyLongDataSource source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
