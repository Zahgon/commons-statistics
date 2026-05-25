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

import java.util.Arrays;
import org.apache.commons.numbers.combinatorics.Factorial;
import org.apache.commons.numbers.combinatorics.LogFactorial;
import org.apache.commons.numbers.core.DD;
import org.apache.commons.numbers.core.DDMath;
import org.apache.commons.numbers.core.Sum;
import org.apache.commons.statistics.inference.SquareMatrixSupport.RealSquareMatrix;

/**
 * Computes the complementary probability for the one-sample Kolmogorov-Smirnov distribution.
 *
 * @since 1.1
 */
final class KolmogorovSmirnovDistribution {

    /**
     * pi^2.
     */
    private static final double PI2 = 9.8696044010893586188344909;

    /**
     * sqrt(2*pi).
     */
    private static final double ROOT_TWO_PI = 2.5066282746310005024157652;

    /**
     * Value of x when the KS sum is 0.5.
     */
    private static final double X_KS_HALF = 0.8275735551899077;

    /**
     * Value of x when the KS sum is 1.0.
     */
    private static final double X_KS_ONE = 0.1754243674345323;

    /**
     * Machine epsilon, 2^-52.
     */
    private static final double EPS = 0x1.0p-52;

    /**
     * No instances.
     */
    private KolmogorovSmirnovDistribution() {
    }

    /**
     * Computes the complementary probability {@code P[D_n >= x]}, or survival function (SF),
     * for the two-sided one-sample Kolmogorov-Smirnov distribution.
     *
     * <pre>
     * D_n = sup_x |F(x) - CDF_n(x)|
     * </pre>
     *
     * <p>where {@code n} is the sample size; {@code CDF_n(x)} is an empirical
     * cumulative distribution function; and {@code F(x)} is the expected
     * distribution.
     *
     * <p>
     * References:
     * <ol>
     * <li>Simard, R., &amp; L’Ecuyer, P. (2011).
     * <a href="https://doi.org/10.18637/jss.v039.i11">Computing the Two-Sided Kolmogorov-Smirnov Distribution.</a>
     * Journal of Statistical Software, 39(11), 1–18.</li>
     * <li>
     * Marsaglia, G., Tsang, W. W., &amp; Wang, J. (2003).
     * <a href="https://doi.org/10.18637/jss.v008.i18">Evaluating Kolmogorov's Distribution.</a>
     * Journal of Statistical Software, 8(18), 1–4.</li>
     * </ol>
     *
     * <p>Note that [2] contains an error in computing h, refer to <a
     * href="https://issues.apache.org/jira/browse/MATH-437">MATH-437</a> for details.
     *
     * @since 1.1
     */
    static final class Two {

        /**
         * pi^2.
         */
        private static final double PI2 = 9.8696044010893586188344909;

        /**
         * pi^4.
         */
        private static final double PI4 = 97.409091034002437236440332;

        /**
         * pi^6.
         */
        private static final double PI6 = 961.38919357530443703021944;

        /**
         * sqrt(2*pi).
         */
        private static final double ROOT_TWO_PI = 2.5066282746310005024157652;

        /**
         * sqrt(pi/2).
         */
        private static final double ROOT_HALF_PI = 1.2533141373155002512078826;

        /**
         * Threshold for Pelz-Good where the 1 - CDF == 1.
         * Occurs when sqrt(2pi/z) exp(-pi^2 / (8 z^2)) is far below 2^-53.
         * Threshold set at exp(-pi^2 / (8 z^2)) = 2^-80.
         */
        private static final double LOG_PG_MIN = -55.451774444795625;

        /**
         * Factor 4a in the quadratic equation to solve max k: log(2^-52) * 8.
         */
        private static final double FOUR_A = -288.3492271129372;

        /**
         * The scaling threshold in the MTW algorithm. Marsaglia used 1e-140. This uses 2^-400 ~ 3.87e-121.
         */
        private static final double MTW_SCALE_THRESHOLD = 0x1.0p-400;

        /**
         * The up-scaling factor in the MTW algorithm. Marsaglia used 1e140. This uses 2^400 ~ 2.58e120.
         */
        private static final double MTW_UP_SCALE = 0x1.0p400;

        /**
         * The power-of-2 of the up-scaling factor in the MTW algorithm, n if the up-scale factor is 2^n.
         */
        private static final int MTW_UP_SCALE_POWER = 400;

        /**
         * The scaling threshold in the Pomeranz algorithm.
         */
        private static final double P_DOWN_SCALE = 0x1.0p-128;

        /**
         * The up-scaling factor in the Pomeranz algorithm.
         */
        private static final double P_UP_SCALE = 0x1.0p128;

        /**
         * The power-of-2 of the up-scaling factor in the Pomeranz algorithm, n if the up-scale factor is 2^n.
         */
        private static final int P_SCALE_POWER = 128;

        /**
         * Maximum finite factorial.
         */
        private static final int MAX_FACTORIAL = 170;

        /**
         * Approximate threshold for ln(MIN_NORMAL).
         */
        private static final int LOG_MIN_NORMAL = -708;

        /**
         * 140, n threshold for small n for the sf computation.
         */
        private static final int N140 = 140;

        /**
         * 0.754693, nxx threshold for small n Durbin matrix sf computation.
         */
        private static final double NXX_0_754693 = 0.754693;

        /**
         * 4, nxx threshold for small n Pomeranz sf computation.
         */
        private static final int NXX_4 = 4;

        /**
         * 2.2, nxx threshold for large n Miller approximation sf computation.
         */
        private static final double NXX_2_2 = 2.2;

        /**
         * 100000, n threshold for large n Durbin matrix sf computation.
         */
        private static final int N_100000 = 100000;

        /**
         * 1.4, nx^(3/2) threshold for large n Durbin matrix sf computation.
         */
        private static final double NX32_1_4 = 1.4;

        /**
         * 1/2.
         */
        private static final double HALF = 0.5;

        /**
         * No instances.
         */
        private Two() {
        }

        /**
         * Calculates complementary probability {@code P[D_n >= x]} for the two-sided
         * one-sample Kolmogorov-Smirnov distribution.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n &ge; x)\)
         */
        static double sf(double x, int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Calculates exact cases for the complementary probability
         * {@code P[D_n >= x]} the two-sided one-sample Kolmogorov-Smirnov distribution.
         *
         * <p>Exact cases handle x not in [0, 1]. It is assumed n is positive.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n &ge; x)\)
         */
        private static double sfExact(double x, int n) {
            if (n * x * x >= 370 || x >= 1) {
                // p would underflow, or x is out of the domain
                return 0;
            }
            final double nx = x * n;
            if (nx <= 1) {
                // x <= 1/(2n)
                if (nx <= HALF) {
                    // Also detects x <= 0 (iff n is positive)
                    return 1;
                }
                if (n == 1) {
                    // Simplification of:
                    // 1 - (n! (2x - 1/n)^n) == 1 - (2x - 1)
                    return 2.0 - 2.0 * x;
                }
                // 1/(2n) < x <= 1/n
                // 1 - (n! (2x - 1/n)^n)
                final double f = 2 * x - 1.0 / n;
                // Switch threshold where (2x - 1/n)^n is sub-normal
                // Max factorial threshold is n=170
                final double logf = Math.log(f);
                if (n <= MAX_FACTORIAL && n * logf > LOG_MIN_NORMAL) {
                    return 1 - Factorial.doubleValue(n) * Math.pow(f, n);
                }
                return -Math.expm1(LogFactorial.create().value(n) + n * logf);
            }
            // 1 - 1/n <= x < 1
            if (n - 1 <= nx) {
                // 2 * (1-x)^n
                return 2 * Math.pow(1 - x, n);
            }
            return -1;
        }

        /**
         * Computes the Durbin matrix approximation for {@code P(D_n < d)} using the method
         * of Marsaglia, Tsang and Wang (2003).
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n &lt; x)\)
         */
        private static double durbinMTW(double x, int n) {
            final int k = (int) Math.ceil(n * x);
            final RealSquareMatrix h = createH(x, n).power(n);
            // Use scaling as per Marsaglia's code to avoid underflow.
            double pFrac = h.get(k - 1, k - 1);
            int scale = h.scale();
            // Omit i == n as this is a no-op
            for (int i = 1; i < n; ++i) {
                pFrac *= (double) i / n;
                if (pFrac < MTW_SCALE_THRESHOLD) {
                    pFrac *= MTW_UP_SCALE;
                    scale -= MTW_UP_SCALE_POWER;
                }
            }
            // Return the CDF
            return clipProbability(Math.scalb(pFrac, scale));
        }

        /**
         * Creates {@code H} of size {@code m x m} as described in [1].
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return H matrix
         */
        private static RealSquareMatrix createH(double x, int n) {
            // MATH-437:
            // This is *not* (int) (n * x) + 1.
            // This is only ever called when 1/n < x < 1 - 1/n.
            // => h cannot be >= 1 when using ceil. h can be 0 if nx is integral.
            final int k = (int) Math.ceil(n * x);
            final double h = k - n * x;
            final int m = 2 * k - 1;
            final double[] data = new double[m * m];
            // Start by filling everything with either 0 or 1.
            for (int i = 0; i < m; ++i) {
                // h[i][j] = i - j + 1 < 0 ? 0 : 1
                // => h[i][j<=i+1] = 1
                final int jend = Math.min(m - 1, i + 1);
                for (int j = i * m; j <= i * m + jend; j++) {
                    data[j] = 1;
                }
            }
            // Setting up power-array to avoid calculating the same value twice:
            // hp[0] = h^1, ..., hp[m-1] = h^m
            final double[] hp = new double[m];
            hp[0] = h;
            for (int i = 1; i < m; ++i) {
                // Avoid compound rounding errors using h * hp[i - 1]
                // with Math.pow as it is within 1 ulp of the exact result
                hp[i] = Math.pow(h, i + 1);
            }
            // First column and last row has special values (each other reversed).
            for (int i = 0; i < m; ++i) {
                data[i * m] -= hp[i];
                data[(m - 1) * m + i] -= hp[m - i - 1];
            }
            // [1] states: "For 1/2 < h < 1 the bottom left element of the matrix should be
            // (1 - 2*h^m + (2h - 1)^m )/m!"
            if (2 * h - 1 > 0) {
                data[(m - 1) * m] += Math.pow(2 * h - 1, m);
            }
            // Aside from the first column and last row, the (i, j)-th element is 1/(i - j + 1)! if i -
            // j + 1 >= 0, else 0. 1's and 0's are already put, so only division with (i - j + 1)! is
            // needed in the elements that have 1's. Note that i - j + 1 > 0 <=> i + 1 > j instead of
            // j'ing all the way to m. Also note that we can use pre-computed factorials given
            // the limits where this method is called.
            for (int i = 0; i < m; ++i) {
                final int im = i * m;
                for (int j = 0; j < i + 1; ++j) {
                    // Here (i - j + 1 > 0)
                    // Divide by (i - j + 1)!
                    // Note: This method is used when:
                    // n <= 140; nxx < 0.754693
                    // n <= 100000; n x^1.5 < 1.4
                    // max m ~ 2nx ~ (1.4/1e5)^(2/3) * 2e5 = 116
                    // Use a tabulated factorial
                    data[im + j] /= Factorial.doubleValue(i - j + 1);
                }
            }
            return SquareMatrixSupport.create(m, data);
        }

        /**
         * Computes the Pomeranz approximation for {@code P(D_n < d)} using the method
         * as described in Simard and L’Ecuyer (2011).
         *
         * <p>Modifications have been made to the scaling of the intermediate values.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n &lt; x)\)
         */
        private static double pomeranz(double x, int n) {
            final double t = n * x;
            // Store floor(A-t) and ceil(A+t). This does not require computing A.
            final int[] amt = new int[2 * n + 3];
            final int[] apt = new int[2 * n + 3];
            computeA(n, t, amt, apt);
            // Precompute ((A[i] - A[i-1])/n)^(j-k) / (j-k)!
            // A[i] - A[i-1] has 4 possible values (based on multiples of A2)
            // A1 - A0 = 0 - 0                               = 0
            // A2 - A1 = A2 - 0                              = A2
            // A3 - A2 = (1 - A2) - A2                       = 1 - 2 * A2
            // A4 - A3 = (A2 + 1) - (1 - A2)                 = 2 * A2
            // A5 - A4 = (1 - A2 + 1) - (A2 + 1)             = 1 - 2 * A2
            // A6 - A5 = (A2 + 1 + 1) - (1 - A2 + 1)         = 2 * A2
            // A7 - A6 = (1 - A2 + 1 + 1) - (A2 + 1 + 1)     = 1 - 2 * A2
            // A8 - A7 = (A2 + 1 + 1 + 1) - (1 - A2 + 1 + 1) = 2 * A2
            // ...
            // Ai - Ai-1 = ((i-1)/2 - A2) - (A2 + (i-2)/2)   = 1 - 2 * A2 ; i = odd
            // Ai - Ai-1 = (A2 + (i-1)/2) - ((i-2)/2 - A2)   = 2 * A2     ; i = even
            // ...
            // A2n+2 - A2n+1 = n - (n - A2)                  = A2
            // ap[][j - k] = ((A[i] - A[i-1])/n)^(j-k) / (j-k)!
            // for each case: A[i] - A[i-1] in [A2, 1 - 2 * A2, 2 * A2]
            // Ignore case 0 as this is not used. Factors are ap[0] = 1, else 0.
            // If A2==0.5 then this is computed as a no-op due to multiplication by zero.
            final int n2 = n + 2;
            final double[][] ap = new double[3][n2];
            final double a2 = Math.min(t - Math.floor(t), Math.ceil(t) - t);
            computeAP(ap[0], a2 / n);
            computeAP(ap[1], (1 - 2 * a2) / n);
            computeAP(ap[2], (2 * a2) / n);
            // Current and previous V
            double[] vc = new double[n2];
            double[] vp = new double[n2];
            // Count of re-scaling
            int scale = 0;
            // V_1,1 = 1
            vc[1] = 1;
            for (int i = 2; i <= 2 * n + 2; i++) {
                final double[] v = vc;
                vc = vp;
                vp = v;
                // This is useful for following current values of vc
                Arrays.fill(vc, 0);
                // Select (A[i] - A[i-1]) factor
                final double[] p;
                if (i == 2 || i == 2 * n + 2) {
                    // First or last
                    p = ap[0];
                } else {
                    // odd:  [1] 1 - 2 * 2A
                    // even: [2] 2 * A2
                    p = ap[2 - (i & 1)];
                }
                // Set limits.
                // j is the ultimate bound for k and should be in [1, n+1]
                final int jmin = Math.max(1, amt[i] + 2);
                final int jmax = Math.min(n + 1, apt[i]);
                final int k1 = Math.max(1, amt[i - 1] + 2);
                // All numbers will reduce in size.
                // Maintain the largest close to 1.0.
                // This is a change from Simard and L’Ecuyer which scaled based on the smallest.
                double max = 0;
                for (int j = jmin; j <= jmax; j++) {
                    final int k2 = Math.min(j, apt[i - 1]);
                    // Accurate sum.
                    // vp[high] is smaller
                    // p[high] is smaller
                    // Sum ascending has smaller products first.
                    double sum = 0;
                    for (int k = k1; k <= k2; k++) {
                        sum += vp[k] * p[j - k];
                    }
                    vc[j] = sum;
                    if (max < sum) {
                        // Note: max *may* always be the first sum: vc[jmin]
                        max = sum;
                    }
                }
                // Rescale if too small
                if (max < P_DOWN_SCALE) {
                    // Only scale in current range from V
                    for (int j = jmin; j <= jmax; j++) {
                        vc[j] *= P_UP_SCALE;
                    }
                    scale -= P_SCALE_POWER;
                }
            }
            // F_n(x) = n! V_{2n+2,n+1}
            double v = vc[n + 1];
            // This method is used when n < 140 where all n! are finite.
            // v is below 1 so we can directly compute the result without using logs.
            v *= Factorial.doubleValue(n);
            // Return the CDF (rescaling as required)
            return Math.scalb(v, scale);
        }

        /**
         * Compute the power factors.
         * <pre>
         * factor[j] = z^j / j!
         * </pre>
         *
         * @param p Power factors.
         * @param z (A[i] - A[i-1]) / n
         */
        private static void computeAP(double[] p, double z) {
            // Note z^0 / 0! = 1 for any z
            p[0] = 1;
            p[1] = z;
            for (int j = 2; j < p.length; j++) {
                // Only used when n <= 140 and can use the tabulated values of n!
                // This avoids using recursion: p[j] = z * p[j-1] / j.
                // Direct computation more closely agrees with the recursion using BigDecimal
                // with 200 digits of precision.
                p[j] = Math.pow(z, j) / Factorial.doubleValue(j);
            }
        }

        /**
         * Compute the factors floor(A-t) and ceil(A+t).
         * Arrays should have length 2n+3.
         *
         * @param n Sample size.
         * @param t Statistic x multiplied by n.
         * @param amt floor(A-t)
         * @param apt ceil(A+t)
         */
        // package-private for testing
        static void computeA(int n, double t, int[] amt, int[] apt) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Computes the Pelz-Good approximation for {@code P(D_n >= d)} as described in
         * Simard and L’Ecuyer (2011).
         *
         * <p>This has been modified to compute the complementary CDF by subtracting the
         * terms k0, k1, k2, k3 from 1. For use in computing the CDF the method should
         * be updated to return the sum of k0 ... k3.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n &ge; x)\)
         * @throws ArithmeticException if the series does not converge
         */
        // package-private for testing
        static double pelzGood(double x, int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Computes the complementary probability {@code P[D_n^+ >= x]} for the one-sided
     * one-sample Kolmogorov-Smirnov distribution.
     *
     * <pre>
     * D_n^+ = sup_x {CDF_n(x) - F(x)}
     * </pre>
     *
     * <p>where {@code n} is the sample size; {@code CDF_n(x)} is an empirical
     * cumulative distribution function; and {@code F(x)} is the expected
     * distribution. The computation uses Smirnov's stable formula:
     *
     * <pre>
     *                   floor(n(1-x)) (n) ( j     ) (j-1)  (         j ) (n-j)
     * P[D_n^+ >= x] = x     Sum       ( ) ( - + x )        ( 1 - x - - )
     *                       j=0       (j) ( n     )        (         n )
     * </pre>
     *
     * <p>Computing using logs is not as accurate as direct multiplication when n is large.
     * However the terms are very large and small. Multiplication uses a scaled representation
     * with a separate exponent term to support the extreme range. Extended precision
     * representation of the numbers reduces the error in the power terms. Details in
     * van Mulbregt (2018).
     *
     * <p>
     * References:
     * <ol>
     * <li>
     * van Mulbregt, P. (2018).
     * <a href="https://doi.org/10.48550/arxiv.1802.06966">Computing the Cumulative Distribution Function and Quantiles of the One-sided Kolmogorov-Smirnov Statistic</a>
     * arxiv:1802.06966.</li>
     * <li>Magg &amp; Dicaire (1971).
     * <a href="https://doi.org/10.1093/biomet/58.3.653">On Kolmogorov-Smirnov Type One-Sample Statistics</a>
     * Biometrika 58.3 pp. 653–656.</li>
     * </ol>
     *
     * @since 1.1
     */
    static final class One {

        /**
         * "Very large" n to use a asymptotic limiting form.
         * [1] suggests 1e12 but this is reduced to avoid excess
         * computation time.
         */
        private static final int VERY_LARGE_N = 1000000;

        /**
         * Maximum number of term for the Smirnov-Dwass algorithm.
         */
        private static final int SD_MAX_TERMS = 3;

        /**
         * Minimum sample size for the Smirnov-Dwass algorithm.
         */
        private static final int SD_MIN_N = 8;

        /**
         * Number of bits of precision in the sum of terms Aj.
         * This does not have to be the full 106 bits of a double-double as the final result
         * is used as a double. The terms are represented as fractions with an exponent:
         * <pre>
         *  Aj = 2^b * f
         *  f of sum(A) in [0.5, 1)
         *  f of Aj in [0.25, 2]
         * </pre>
         * <p>The terms can be added if their exponents overlap. The bits of precision must
         * account for the extra range of the fractional part of Aj by 1 bit. Note that
         * additional bits are added to this dynamically based on the number of terms.
         */
        private static final int SUM_PRECISION_BITS = 53;

        /**
         * Number of bits of precision in the sum of terms Aj.
         * For Smirnov-Dwass we use the full 106 bits of a double-double due to the summation
         * of terms that cancel. Account for the extra range of the fractional part of Aj by 1 bit.
         */
        private static final int SD_SUM_PRECISION_BITS = 107;

        /**
         * Proxy for the default choice of the scaled power function.
         * The actual choice is based on the chosen algorithm.
         */
        private static final ScaledPower POWER_DEFAULT = null;

        /**
         * Defines a scaled power function.
         * Package-private to allow the main sf method to be called direct in testing.
         */
        @FunctionalInterface
        interface ScaledPower {

            /**
             * Compute the number {@code x} raised to the power {@code n}.
             *
             * <p>The value is returned as fractional {@code f} and integral
             * {@code 2^exp} components.
             * <pre>
             * (x+xx)^n = (f+ff) * 2^exp
             * </pre>
             *
             * @param x x.
             * @param n Power.
             * @param exp Result power of two scale factor (integral exponent).
             * @return Fraction part.
             * @see DD#frexp(int[])
             * @see DD#pow(int, long[])
             * @see DDMath#pow(DD, int, long[])
             */
            DD pow(DD x, int n, long[] exp);
        }

        /**
         * No instances.
         */
        private One() {
        }

        /**
         * Calculates complementary probability {@code P[D_n^+ >= x]}, or survival
         * function (SF), for the one-sided one-sample Kolmogorov-Smirnov distribution.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n^+ &ge; x)\)
         */
        static double sf(double x, int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Calculates exact cases for the complementary probability
         * {@code P[D_n^+ >= x]} the one-sided one-sample Kolmogorov-Smirnov distribution.
         *
         * <p>Exact cases handle x not in [0, 1]. It is assumed n is positive.
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n^+ &ge; x)\)
         */
        private static double sfExact(double x, int n) {
            if (n * x * x >= 372.5 || x >= 1) {
                // p would underflow, or x is out of the domain
                return 0;
            }
            if (x <= 0) {
                // edge-of, or out-of, the domain
                return 1;
            }
            if (n == 1) {
                return x;
            }
            // x <= 1/n
            // [1] Equation (33)
            final double nx = n * x;
            if (nx <= 1) {
                // 1 - x (1+x)^(n-1): here x may be small so use log1p
                return 1 - x * Math.exp((n - 1) * Math.log1p(x));
            }
            // 1 - 1/n <= x < 1
            // [1] Equation (16)
            if (n - 1 <= nx) {
                // (1-x)^n: here x > 0.5 and 1-x is exact
                return Math.pow(1 - x, n);
            }
            return -1;
        }

        /**
         * Calculates complementary probability {@code P[D_n^+ >= x]}, or survival
         * function (SF), for the one-sided one-sample Kolmogorov-Smirnov distribution.
         *
         * <p>Computes the result using the asymptotic formula Eq 5 in [1].
         *
         * @param x Statistic.
         * @param n Sample size (assumed to be positive).
         * @return \(P(D_n^+ &ge; x)\)
         */
        private static double sfAsymptotic(double x, int n) {
            // Magg & Dicaire (1971) limiting form
            return Math.exp(-Math.pow(6.0 * n * x + 1, 2) / (18.0 * n));
        }

        /**
         * Calculates complementary probability {@code P[D_n^+ >= x]}, or survival
         * function (SF), for the one-sided one-sample Kolmogorov-Smirnov distribution.
         *
         * <p>Computes the result using double-double arithmetic. The power function
         * can use a fast approximation or a full power computation.
         *
         * <p>This function is safe for {@code x > 1/n}. When {@code x} approaches
         * sub-normal then division or multiplication by x can under/overflow. The
         * case of {@code x < 1/n} can be computed in {@code sfExact}.
         *
         * @param x Statistic (typically in (1/n, 1 - 1/n)).
         * @param n Sample size (assumed to be positive).
         * @param power Function to compute the scaled power (can be null).
         * @return \(P(D_n^+ &ge; x)\)
         * @see DD#pow(int, long[])
         * @see DDMath#pow(DD, int, long[])
         */
        static double sf(double x, int n, ScaledPower power) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute exactly {@code x = (k + alpha) / n} with {@code k} an integer and
         * {@code alpha in [0, 1)}. Note that {@code k ~ floor(nx)} but may be rounded up
         * if {@code alpha -> 1} within working precision.
         *
         * <p>This computation is a significant source of increased error if performed in
         * 64-bit arithmetic. Although the value alpha is only used for the PDF computation
         * a value of {@code alpha == 0} indicates the final term of the SF summation can be
         * dropped due to the cancellation of a power term {@code (x + j/n)} to zero with
         * {@code x = (n-j)/n}. That is if {@code alpha == 0} then x is the fraction {@code k/n}
         * and one Aj term is zero.
         *
         * @param n Sample size.
         * @param x Statistic.
         * @param alpha Output alpha.
         * @return k
         */
        static int splitX(int n, double x, double[] alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Returns {@code floor(log2(n))}.
         *
         * @param n Value.
         * @return approximate log2(n)
         */
        private static int log2(int n) {
            return 31 - Integer.numberOfLeadingZeros(n);
        }
    }

    /**
     * Computes {@code P(sqrt(n) D_n > x)}, the limiting form for the distribution of
     * Kolmogorov's D_n as described in Simard and L’Ecuyer (2011) (Eq. 5, or K0 Eq. 6).
     *
     * <p>Computes \( 2 \sum_{i=1}^\infty (-1)^(i-1) e^{-2 i^2 x^2} \), or
     * \( 1 - (\sqrt{2 \pi} / x) * \sum_{i=1}^\infty { e^{-(2i-1)^2 \pi^2 / (8x^2) } } \)
     * when x is small.
     *
     * <p>Note: This computes the upper Kolmogorov sum.
     *
     * @param x Argument x = sqrt(n) * d
     * @return Upper Kolmogorov sum evaluated at x
     */
    static double ksSum(double x) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clip the probability to the range [0, 1].
     *
     * @param p Probability.
     * @return p in [0, 1]
     */
    static double clipProbability(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
