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

import java.util.function.DoubleUnaryOperator;
import org.apache.commons.numbers.core.Precision;

/**
 * For a function defined on some interval {@code (lo, hi)}, this class
 * finds an approximation {@code x} to the point at which the function
 * attains its minimum.
 * It implements Richard Brent's algorithm (from his book "Algorithms for
 * Minimization without Derivatives", p. 79) for finding minima of real
 * univariate functions.
 *
 * <P>This code is an adaptation, partly based on the Python code from SciPy
 * (module "optimize.py" v0.5); the original algorithm is also modified:
 * <ul>
 *  <li>to use an initial guess provided by the user,</li>
 *  <li>to ensure that the best point encountered is the one returned.</li>
 * </ul>
 *
 * <p>This class has been extracted from {@code o.a.c.math4.optim.univariate}
 * and simplified to remove support for the UnivariateOptimizer interface.
 * This removed the options: to find the maximum; use a custom convergence checker
 * on the function value; and remove the maximum function evaluation count.
 * The class now implements a single optimize method within the provided bracket
 * from the given start position (with value).
 *
 * @since 1.1
 */
final class BrentOptimizer {

    /**
     * Golden section. (3 - sqrt(5)) / 2.
     */
    private static final double GOLDEN_SECTION = 0.3819660112501051;

    /**
     * Minimum relative tolerance. 2 * eps = 2^-51.
     */
    private static final double MIN_RELATIVE_TOLERANCE = 0x1.0p-51;

    /**
     * Relative threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute threshold.
     */
    private final double absoluteThreshold;

    /**
     * The number of function evaluations from the most recent call to optimize.
     */
    private int evaluations;

    /**
     * This class holds a point and the value of an objective function at this
     * point. This is a simple immutable container.
     *
     * @since 1.1
     */
    static final class PointValuePair {

        /**
         * Point.
         */
        private final double point;

        /**
         * Value of the objective function at the point.
         */
        private final double value;

        /**
         * @param point Point.
         * @param value Value of an objective function at the point.
         */
        private PointValuePair(double point, double value) {
            this.point = point;
            this.value = value;
        }

        /**
         * Create a point/objective function value pair.
         *
         * @param point Point.
         * @param value Value of an objective function at the point.
         * @return the pair
         */
        static PointValuePair of(double point, double value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the point.
         *
         * @return the point.
         */
        double getPoint() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the value of the objective function.
         *
         * @return the stored value of the objective function.
         */
        double getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The arguments are used to implement the original stopping criterion
     * of Brent's algorithm.
     * {@code abs} and {@code rel} define a tolerance
     * {@code tol = rel |x| + abs}. {@code rel} should be no smaller than
     * <em>2 macheps</em> and preferably not much less than <em>sqrt(macheps)</em>,
     * where <em>macheps</em> is the relative machine precision. {@code abs} must
     * be positive.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @throws IllegalArgumentException if {@code abs <= 0}; or if {@code rel < 2 * Math.ulp(1.0)}
     */
    BrentOptimizer(double rel, double abs) {
        if (rel >= MIN_RELATIVE_TOLERANCE) {
            relativeThreshold = rel;
            absoluteThreshold = Arguments.checkStrictlyPositive(abs);
        } else {
            // relative too small, or NaN
            throw new InferenceException(InferenceException.X_LT_Y, rel, MIN_RELATIVE_TOLERANCE);
        }
    }

    /**
     * Gets the number of function evaluations from the most recent call to
     * {@link #optimize(DoubleUnaryOperator, double, double, double, double) optimize}.
     *
     * @return the function evaluations
     */
    int getEvaluations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Search for the minimum inside the provided interval. The bracket must satisfy
     * the equalities {@code lo < mid < hi} or {@code hi < mid < lo}.
     *
     * <p>Note: This function accepts the initial guess and the function value at that point.
     * This is done for convenience as this internal class is used where the caller already
     * knows the function value.
     *
     * @param func Function to solve.
     * @param lo Lower bound of the search interval.
     * @param hi Higher bound of the search interval.
     * @param mid Start point.
     * @param fMid Function value at the start point.
     * @return the value where the function is minimum.
     * @throws IllegalArgumentException if start point is not within the search interval
     * @throws IllegalStateException if the maximum number of iterations is exceeded
     */
    PointValuePair optimize(DoubleUnaryOperator func, double lo, double hi, double mid, double fMid) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
