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
import java.util.function.DoubleSupplier;
import java.util.function.LongConsumer;

/**
 * Computes the variance of the available values.
 *
 * <p>This is a copy of {@code o.a.c.statistics.descriptive.LongVariance} to allow benchmarking.
 * This uses {@code java.lang.Math.multiplyHigh(long, long)} and requires Java 11+.
 *
 * @see <a href="https://en.wikipedia.org/wiki/variance">variance (Wikipedia)</a>
 * @since 1.1
 */
final class LongVariance2 implements LongConsumer, DoubleSupplier {

    /**
     * Sum of the squared values.
     */
    private final UInt192 sumSq;

    /**
     * Sum of the values.
     */
    private final Int128 sum;

    /**
     * Count of values that have been added.
     */
    private long n;

    /**
     * Flag to control if the statistic is biased, or should use a bias correction.
     */
    private boolean biased;

    /**
     * Create an instance.
     */
    private LongVariance2() {
        this(UInt192.create(), Int128.create(), 0);
    }

    /**
     * Create an instance.
     *
     * @param sumSq Sum of the squared values.
     * @param sum Sum of the values.
     * @param n Count of values that have been added.
     */
    private LongVariance2(UInt192 sumSq, Int128 sum, int n) {
        this.sumSq = sumSq;
        this.sum = sum;
        this.n = n;
    }

    /**
     * Creates an instance.
     *
     * <p>The initial result is {@code NaN}.
     *
     * @return {@code IntVariance} instance.
     */
    public static LongVariance2 create() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an instance populated using the input {@code values}.
     *
     * @param values Values.
     * @return {@code IntVariance} instance.
     */
    public static LongVariance2 of(long... values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Updates the state of the statistic to reflect the addition of {@code value}.
     *
     * @param value Value.
     */
    @Override
    public void accept(long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the variance of all input values.
     *
     * <p>When no values have been added, the result is {@code NaN}.
     *
     * @return variance of all values.
     */
    @Override
    public double getAsDouble() {
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
     * Combine with the {@code other} instance.
     *
     * @param other Other instance.
     * @return this instance
     */
    public LongVariance2 combine(LongVariance2 other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the value of the biased flag. The default value is {@code false}.
     *
     * <p>If {@code false} the sum of squared deviations from the sample mean is
     * normalised by {@code n - 1} where {@code n} is the number of samples. This is
     * Bessel's correction for an unbiased estimator of the variance of a hypothetical
     * infinite population.
     *
     * <p>If {@code true} the sum of squared deviations is normalised by the number of
     * samples {@code n}.
     *
     * <p>Note: This option only applies when {@code n > 1}. The variance of {@code n = 1}
     * is always 0.
     *
     * <p>This flag only controls the final computation of the statistic. The value of
     * this flag will not affect compatibility between instances during a
     * {@link #combine(LongVariance2) combine} operation.
     *
     * @param v Value.
     * @return {@code this} instance
     */
    public LongVariance2 setBiased(boolean v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
