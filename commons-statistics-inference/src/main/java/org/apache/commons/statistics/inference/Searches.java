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

import java.util.function.IntToDoubleFunction;

/**
 * Search utility methods.
 *
 * @since 1.1
 */
final class Searches {

    /**
     * Range threshold to use a binary search.
     * The binary search takes O(log(n)) so is used when n is large and a sequential
     * search is slower.
     */
    private static final int BINARY_SEARCH = 8;

    /**
     * No instances.
     */
    private Searches() {
    }

    /**
     * Conduct a search between {@code a} inclusive and {@code b} inclusive
     * to find the lowest index where {@code value <= x}. The values must be
     * in <em>descending</em> order. The method is functionally equivalent to:
     * <pre>
     * {@code
     * i = b + 1
     * while (i > a AND value(i - 1) <= x)
     *    i = i - 1
     * return i
     * }</pre>
     *
     * <p>The function is only evaluated between the closed interval {@code [a, b]}.
     * Special cases:
     * <ul>
     * <li>If {@code value(a) <= x} the returned index is {@code a}.</li>
     * <li>If {@code value(b) > x} the returned index is {@code b + 1}.</li>
     * </ul>
     *
     * @param a Lower limit (inclusive).
     * @param b Upper limit (inclusive).
     * @param x Target value.
     * @param value Function to evaluate the value at an index.
     * @return the minimum index where {@code value(i) <= x}.
     */
    static int searchDescending(int a, int b, double x, IntToDoubleFunction value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Conduct a search between {@code a} inclusive and {@code b} inclusive
     * to find the highest index where {@code value <= x}. The values must be
     * in <em>ascending</em> order. The method is functionally equivalent to:
     * <pre>
     * {@code
     * i = a - 1
     * while (i < b AND value(i + 1) <= x)
     *    i = i + 1
     * return i
     * }</pre>
     *
     * <p>The function is only evaluated between the closed interval {@code [a, b]}.
     * Special cases:
     * <ul>
     * <li>If {@code value(a) > x} the returned index is {@code a - 1}.</li>
     * <li>If {@code value(b) <= x} the returned index is {@code b}.</li>
     * </ul>
     *
     * @param a Lower limit (inclusive).
     * @param b Upper limit (inclusive).
     * @param x Target value.
     * @param value Function to evaluate the value at an index.
     * @return the maximum index where {@code value(i) <= x}.
     */
    static int searchAscending(int a, int b, double x, IntToDoubleFunction value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
