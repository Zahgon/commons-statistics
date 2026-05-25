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
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;
import org.apache.commons.numbers.core.DD;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.statistics.descriptive.StatisticResult;
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
 * Executes a benchmark of the interpolation of {@code long} values
 * {@code a} and {@code b}, where {@code a <= b} using the interpolant
 * {@code t} is in {@code [0, 1]}.
 * <pre>
 * value = a + t * (b - a)
 * </pre>
 * <p>{@code b - a} has a 64-bit unsigned value and {@code t} is a 53-bit
 * double; the result {@code t * (b - a)} has a maximum of 117-bits and
 * requires extended precision for the exact result. This benchmark compares
 * implementations of extended precision arithmetic to compute the result.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class InterpolationPerformance {

    /**
     * A fast generate of long values.
     */
    private static final RandomSource RANDOM_SOURCE = RandomSource.XO_RO_SHI_RO_128_PP;

    /**
     * Source of {@code long} data to interpolate as {@code t * (b - a)} with {@code t} in
     * [0, 1].
     */
    @State(Scope.Benchmark)
    public static class DataSource {

        /**
         * Data bit depth. Default to a range that requires extended precision and
         * a range possible using double arithmetic but (b - a) is not a 32-bit integer.
         */
        @Param({ "60", "40" })
        private int bitDepth;

        /**
         * Data size.
         */
        @Param({ "1024" })
        private int size;

        /**
         * Min values.
         */
        private long[] a;

        /**
         * Max values.
         */
        private long[] b;

        /**
         * Interpolants.
         */
        private double[] t;

        /**
         * @return the min values {@code b}
         */
        public long[] getA() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the max values {@code b}
         */
        public long[] getB() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the interpolant {@code t}
         */
        public double[] getT() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data. Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The interpolation function.
     */
    public interface InterpolationFunction {

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <pre>
         * value = a + t * (b - a)
         * </pre>
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        StatisticResult interpolate(long a, long b, double t);
    }

    /**
     * Source of {@code long} data to interpolate as {@code t * (b - a)} with {@code t} in
     * [0, 1].
     *
     * <p>This class generates a sample for interpolation and then evaluates the result
     * using the provided function.
     */
    @State(Scope.Benchmark)
    public static class DynamicDataSource {

        /**
         * Data bit depth.
         */
        @Param({ "64", "32" })
        private int bitDepth;

        /**
         * Source of randomness.
         */
        private UniformRandomProvider gen;

        /**
         * Create the source of randomness.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Evaluate the function using a random sample of the arguments.
         *
         * @param fun Function.
         * @return the result
         */
        StatisticResult evaluate(InterpolationFunction fun) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of {@code long} interpolation function.
     */
    @State(Scope.Benchmark)
    public static class InterpolationSource {

        /**
         * 0.5.
         */
        private static final double HALF = 0.5;

        /**
         * The value 2^53 converted for comparison as an unsigned integer.
         */
        private static final long UNSIGNED_2_POW_53 = Long.MIN_VALUE + (1L << 53);

        /**
         * The value 2^63.
         */
        private static final BigInteger TWO_POW_63 = BigInteger.ONE.shiftLeft(63);

        /**
         * Name of the source.
         */
        @Param({ "BigDecimal", "DD", "DD2", "DD3", "DD4", "DD5", "Hybrid", "Partial", "double", "long", "null" })
        private String name;

        /**
         * Interpolation function.
         */
        private InterpolationFunction fun;

        /**
         * @return the function
         */
        public InterpolationFunction getFunction() {
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
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses BigDecimal for an exact result.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateBigDecimal(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) to compute {@code a + t * (b - a)}. Note that the
         * use of a double-double (106-bits) cannot compute the delta without round-off
         * loss of precision: t * (b - a) => 53-bit * (64-bit unsigned) == 117-bit result.
         * The double result would be within 1 ULP but rounding to integer on the ties
         * boundary can be incorrect.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateDD(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) to compute {@code t * (b - a)}. This is then
         * decomposed into an integer and fractional part using exact operations.
         * The integer can be added to {@code a} and the fraction used for rounding.
         *
         * <p>This method may still have incorrect rounding if the product
         * {@code t * (b - a)} exceeds the 106-bit precision of the DD. The method
         * is most robust than {@link #interpolateDD(long, long, double)} as it has
         * only one computation where bits can be lost.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateDD2(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) when the exact result is representable using
         * 106-bits, otherwise BigDecimal.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateHybrid(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) when the exact result is representable using
         * 106-bits, otherwise BigDecimal.
         *
         * <p>Differs from {@link #interpolateHybrid(long, long, double)} in the
         * BigDecimal computation by returning a result with lazy evaluation of the long
         * or double result.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolatePartial(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses the evaluated {@code double} result from
         * {@link #interpolatePartial(long, long, double)}.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolatePartialAsDouble(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses the evaluated {@code long} result from
         * {@link #interpolatePartial(long, long, double)}.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolatePartial2AsLong(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) to compute {@code t * (b - a)}. When (b - a)
         * is greater than 63-bits this is performed as two multiplications of
         * the value split into two. Each multiplication is exact. The two results
         * are then decomposed into integer and fractional parts. The fraction
         * parts are then added with possible loss of bits and rounding the
         * integer result may not be correct.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateDD3(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Uses double-double (DD) to compute {@code t * (b - a)}. When (b - a)
         * is greater than 63-bits this is performed as two multiplications of
         * the value split into two. Each multiplication is exact. The two results
         * are then decomposed into integer and fractional parts. The fraction
         * part are added with special a double-double routine with no loss of bits
         * and rounding to an integer is exact.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateDD4(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Linear interpolation between <strong>sorted</strong> values {@code a <= b}
         * using the interpolant {@code t}.
         *
         * <p>Differences (b - a) above 2^53 are the same as
         * {@link #interpolateDD3(long, long, double)}. Below 2^53 uses double arithmetic.
         * This can have integer rounding errors in extreme cases, and frequent ULP errors
         * for the double value.
         *
         * @param a Min value.
         * @param b Max value.
         * @param t Interpolant in (0, 1).
         * @return the value
         */
        static StatisticResult interpolateDD5(long a, long b, double t) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // DD routines taken from o.a.c.numbers.core.DD and DDMath
        /**
         * Compute the sum of {@code (x, xx)} and {@code (y, yy)} to triple-double precision.
         *
         * @param x High part of x.
         * @param xx Low part of x.
         * @param y High part of y.
         * @param yy Low part of y.
         * @param s12 Output parts (s1, s2)
         * @return s0
         */
        private static double add3(double x, double xx, double y, double yy, double[] s12) {
            // Expansion sum (Schewchuk Fig 7): (x, xx) + (x, yy) -> (s0, s1, s2, s3)
            // --- adapted from DD.accurateAdd ---
            DD s = DD.ofSum(xx, yy);
            double s3 = s.lo();
            s = DD.ofSum(x, s.hi());
            // (s0, s1, s2) == (s.hi(), s.lo(), s3)
            double s0 = s.hi();
            s = DD.ofSum(s.lo(), y);
            double s2 = s.lo();
            s = DD.ofSum(s0, s.hi());
            // (s0, s1, s2, s3) == (s.hi(), s.lo(), s2, s3)
            // Compress (Schewchuk Fig. 15) (s0, s1, s2, s3) -> (g0, g1, g2, g3)
            // --- adapted from DDMath.norm3 ---
            double q = s.lo();
            final double g0 = s.hi();
            final double g1 = q + s2;
            q = fastTwoSumLow(q, s2, g1);
            final double g2 = q + s3;
            final double g3 = fastTwoSumLow(q, s3, g2);
            // (g0, g1, g2, g3) -> (h0, h1, h2, h3), returned as (h0, h1, h2 + h3)
            q = g1 + g2;
            s12[1] = fastTwoSumLow(g1, g2, q) + g3;
            final double h0 = g0 + q;
            s12[0] = fastTwoSumLow(g0, q, h0);
            return h0;
        }

        /**
         * Compute the round-off of the sum of two numbers {@code a} and {@code b} using
         * Dekker's two-sum algorithm. The values are required to be ordered by magnitude:
         * {@code |a| >= |b|}.
         *
         * <p>If {@code a} is zero and {@code b} is non-zero the returned value is zero.
         *
         * @param a First part of sum.
         * @param b Second part of sum.
         * @param x Sum.
         * @return the sum round-off
         */
        private static double fastTwoSumLow(double a, double b, double x) {
            // (x, xx) = a + b
            // bVirtual = x - a
            // xx = b - bVirtual
            return b - (x - a);
        }
    }

    /**
     * Create the interpolation using the function. Uses pre-computed arguments.
     *
     * @param source Source of the data.
     * @param function Source of the function.
     * @return the interpolation result
     */
    @Benchmark
    public StatisticResult[] interpolate(DataSource source, InterpolationSource function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create the interpolation using the function. Dynamically computes arguments.
     * This benchmark should avoid branch prediction that can be done of fixed input data.
     * The cost of data generation is small compared to the evaluation.
     *
     * @param source Source of the data.
     * @param function Source of the function.
     * @return the interpolation result
     */
    @Benchmark
    public StatisticResult evaluate(DynamicDataSource source, InterpolationSource function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
