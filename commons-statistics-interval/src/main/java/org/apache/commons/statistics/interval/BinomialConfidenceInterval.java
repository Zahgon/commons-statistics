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
package org.apache.commons.statistics.interval;

import org.apache.commons.statistics.distribution.BetaDistribution;
import org.apache.commons.statistics.distribution.NormalDistribution;

/**
 * Generate confidence intervals for a binomial proportion.
 *
 * <p>Note: To avoid <em>overshoot</em>, the confidence intervals are clipped to be in the
 * {@code [0, 1]} interval in the case of the {@link #NORMAL_APPROXIMATION normal
 * approximation} and {@link #AGRESTI_COULL Agresti-Coull} methods.
 *
 * @see <a
 * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval">Binomial
 * proportion confidence interval (Wikipedia)</a>
 *
 * @since 1.2
 */
public enum BinomialConfidenceInterval {

    /**
     * Implements the normal approximation method for creating a binomial proportion
     * confidence interval.
     *
     * <p>This method clips the confidence interval to be in {@code [0, 1]}.
     *
     * @see <a
     * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Problems_with_using_a_normal_approximation_or_%22Wald_interval%22">
     * Normal approximation interval (Wikipedia)</a>
     */
    NORMAL_APPROXIMATION {

        @Override
        Interval create(int n, int x, double alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Implements the Wilson score method for creating a binomial proportion confidence
     * interval.
     *
     * @see <a
     * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Wilson_score_interval">
     * Normal approximation interval (Wikipedia)</a>
     */
    WILSON_SCORE {

        @Override
        Interval create(int n, int x, double alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Implements the Jeffreys method for creating a binomial proportion confidence
     * interval.
     *
     * <p>In order to avoid the coverage probability tending to zero when {@code p} tends
     * towards 0 or 1, when {@code x = 0} the lower limit is set to 0, and when
     * {@code x = n} the upper limit is set to 1.
     *
     * @see <a
     * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Jeffreys_interval">
     * Jeffreys interval (Wikipedia)</a>
     */
    JEFFREYS {

        @Override
        Interval create(int n, int x, double alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Implements the Clopper-Pearson method for creating a binomial proportion confidence
     * interval.
     *
     * @see <a
     * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Clopper%E2%80%93Pearson_interval">
     * Clopper-Pearson interval (Wikipedia)</a>
     */
    CLOPPER_PEARSON {

        @Override
        Interval create(int n, int x, double alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Implements the Agresti-Coull method for creating a binomial proportion confidence
     * interval.
     *
     * <p>This method clips the confidence interval to be in {@code [0, 1]}.
     *
     * @see <a
     * href="https://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Agresti%E2%80%93Coull_interval">
     * Agresti-Coull interval (Wikipedia)</a>
     */
    AGRESTI_COULL {

        @Override
        Interval create(int n, int x, double alpha) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ;

    /**
     * The standard normal distribution.
     */
    static final NormalDistribution NORMAL_DISTRIBUTION = NormalDistribution.of(0, 1);

    /**
     * Create a confidence interval for the true probability of success of an unknown
     * binomial distribution with the given observed number of trials, successes and error
     * rate.
     *
     * <p>The error rate {@code alpha} is related to the confidence level that the
     * interval contains the true probability of success as
     * {@code alpha = 1 - confidence}, where {@code confidence} is the confidence level
     * in {@code [0, 1]}. For example a 95% confidence level is an {@code alpha} of 0.05.
     *
     * @param numberOfTrials Number of trials.
     * @param numberOfSuccesses Number of successes.
     * @param alpha Desired error rate that the true probability of success falls <em>outside</em>
     * the returned interval.
     * @return Confidence interval containing the probability of success with error rate
     * {@code alpha}
     * @throws IllegalArgumentException if {@code numberOfTrials <= 0}, if
     * {@code numberOfSuccesses < 0}, if {@code numberOfSuccesses > numberOfTrials}, or if
     * {@code alpha} is not in the open interval {@code (0, 1)}.
     */
    public Interval fromErrorRate(int numberOfTrials, int numberOfSuccesses, double alpha) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create a confidence interval for the true probability of success of an unknown
     * binomial distribution with the given observed number of trials, successes and error
     * rate.
     *
     * @param n Number of trials.
     * @param x Number of successes.
     * @param alpha Desired error rate.
     * @return Confidence interval
     */
    abstract Interval create(int n, int x, double alpha);

    /**
     * Clip the probability to [0, 1].
     *
     * @param p Probability.
     * @return the probability in [0, 1]
     */
    static double clip(double p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
