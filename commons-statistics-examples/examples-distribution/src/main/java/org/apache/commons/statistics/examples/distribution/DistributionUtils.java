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
package org.apache.commons.statistics.examples.distribution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.numbers.core.Precision;
import org.apache.commons.statistics.distribution.ContinuousDistribution;
import org.apache.commons.statistics.distribution.DiscreteDistribution;

/**
 * Utility methods.
 */
final class DistributionUtils {

    /**
     * Message prefix for an unknown function.
     */
    private static final String UNKNOWN_FUNCTION = "Unknown function: ";

    /**
     * Maximum relative error for equality in the 'check' command.
     */
    private static final double MAX_RELATIVE_ERROR = 1e-6;

    /**
     * Maximum absolute error for equality to 0 or 1 for a probability.
     */
    private static final double DELTA_P = 1e-6;

    /**
     * No public construction.
     */
    private DistributionUtils() {
    }

    /**
     * A unary function for a continuous distribution.
     */
    interface ContinuousFunction {

        /**
         * Applies this function to the given argument.
         *
         * @param dist Distributions
         * @param x Point to evaluate
         * @return the result
         */
        double apply(ContinuousDistribution dist, double x);
    }

    /**
     * A unary function for a discrete distribution.
     */
    interface DiscreteFunction {

        /**
         * Applies this function to the given argument.
         *
         * @param dist Distributions
         * @param x Point to evaluate
         * @return the result
         */
        double apply(DiscreteDistribution dist, int x);
    }

    /**
     * A unary inverse function for a discrete distribution.
     */
    interface InverseDiscreteFunction {

        /**
         * Applies this function to the given argument.
         *
         * @param dist Distributions
         * @param x Point to evaluate
         * @return the result
         */
        int apply(DiscreteDistribution dist, double x);
    }

    /**
     * Evaluate the distribution function.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void evaluate(List<Distribution<ContinuousDistribution>> dist, ContinuousDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Evaluate the continuous distribution inverse function.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void evaluate(List<Distribution<ContinuousDistribution>> dist, InverseContinuousDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Evaluate the discrete distribution function.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void evaluate(List<Distribution<DiscreteDistribution>> dist, DiscreteDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Evaluate the discrete distribution inverse function.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void evaluate(List<Distribution<DiscreteDistribution>> dist, InverseDiscreteDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Verification checks on the continuous distribution.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void check(List<Distribution<ContinuousDistribution>> dist, ContinuousDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Verification checks on the discrete distribution.
     *
     * @param dist Distributions
     * @param distributionOptions Distribution options
     */
    static void check(List<Distribution<DiscreteDistribution>> dist, DiscreteDistributionOptions distributionOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the output.
     *
     * @param distributionOptions Distribution options
     * @return the print stream
     */
    private static PrintWriter createOutput(DistributionOptions distributionOptions) {
        if (distributionOptions.outputFile != null) {
            try {
                return new PrintWriter(Files.newBufferedWriter(distributionOptions.outputFile.toPath()));
            } catch (IOException ex) {
                throw new UncheckedIOException("Failed to create output: " + distributionOptions.outputFile, ex);
            }
        }
        return new PrintWriter(System.out) {

            @Override
            public void close() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    /**
     * Creates the delimiter.
     *
     * @param distributionOptions Distribution options
     * @return the delimiter
     */
    private static String createDelimiter(DistributionOptions distributionOptions) {
        final String delim = distributionOptions.delim;
        // Unescape tabs. Do not support other escaped delimiters.
        return delim.replace("\\t", "\t");
    }

    /**
     * Creates the header.
     *
     * @param <T> Type of distribution
     * @param xname Name for the evaluated point
     * @param dist List of named distributions
     * @param out Output
     * @param delim Field delimiter
     */
    private static <T> void createHeader(String xname, List<Distribution<T>> dist, final PrintWriter out, final String delim) {
        // Create header
        out.print(xname);
        dist.forEach(d -> {
            out.print(delim);
            out.print(d.getParameters());
        });
        out.println();
    }

    /**
     * Creates the function.
     *
     * @param distributionOptions Distribution options
     * @return the function
     */
    private static ContinuousFunction createFunction(ContinuousDistributionOptions distributionOptions) {
        ContinuousFunction f;
        switch(distributionOptions.distributionFunction) {
            case PDF:
                f = ContinuousDistribution::density;
                break;
            case LPDF:
                f = ContinuousDistribution::logDensity;
                break;
            case CDF:
                f = ContinuousDistribution::cumulativeProbability;
                break;
            case SF:
                f = ContinuousDistribution::survivalProbability;
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_FUNCTION + distributionOptions.distributionFunction);
        }
        if (!distributionOptions.suppressException) {
            return f;
        }
        return new ContinuousFunction() {

            @Override
            public double apply(ContinuousDistribution dist, double x) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    /**
     * Creates the function.
     *
     * @param distributionOptions Distribution options
     * @return the function
     */
    private static DiscreteFunction createFunction(DiscreteDistributionOptions distributionOptions) {
        DiscreteFunction f;
        switch(distributionOptions.distributionFunction) {
            case PMF:
                f = DiscreteDistribution::probability;
                break;
            case LPMF:
                f = DiscreteDistribution::logProbability;
                break;
            case CDF:
                f = DiscreteDistribution::cumulativeProbability;
                break;
            case SF:
                f = DiscreteDistribution::survivalProbability;
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_FUNCTION + distributionOptions.distributionFunction);
        }
        if (!distributionOptions.suppressException) {
            return f;
        }
        return new DiscreteFunction() {

            @Override
            public double apply(DiscreteDistribution dist, int x) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    /**
     * Creates the function.
     *
     * @param distributionOptions Distribution options
     * @return the function
     */
    private static ContinuousFunction createFunction(InverseContinuousDistributionOptions distributionOptions) {
        ContinuousFunction f;
        switch(distributionOptions.distributionFunction) {
            case ICDF:
                f = ContinuousDistribution::inverseCumulativeProbability;
                break;
            case ISF:
                f = ContinuousDistribution::inverseSurvivalProbability;
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_FUNCTION + distributionOptions.distributionFunction);
        }
        if (!distributionOptions.suppressException) {
            return f;
        }
        return new ContinuousFunction() {

            @Override
            public double apply(ContinuousDistribution dist, double x) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    /**
     * Creates the function.
     *
     * @param distributionOptions Distribution options
     * @return the function
     */
    private static InverseDiscreteFunction createFunction(InverseDiscreteDistributionOptions distributionOptions) {
        InverseDiscreteFunction f;
        switch(distributionOptions.distributionFunction) {
            case ICDF:
                f = DiscreteDistribution::inverseCumulativeProbability;
                break;
            case ISF:
                f = DiscreteDistribution::inverseSurvivalProbability;
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_FUNCTION + distributionOptions.distributionFunction);
        }
        if (!distributionOptions.suppressException) {
            return f;
        }
        return new InverseDiscreteFunction() {

            @Override
            public int apply(DiscreteDistribution dist, double x) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    /**
     * Creates the points.
     *
     * @param distributionOptions Distribution options
     * @return the points
     */
    private static double[] createPoints(ContinuousDistributionOptions distributionOptions) {
        if (distributionOptions.x != null) {
            return distributionOptions.x;
        }
        if (distributionOptions.inputFile != null) {
            return readDoublePoints(distributionOptions.inputFile);
        }
        return enumerate(distributionOptions.min, distributionOptions.max, distributionOptions.steps);
    }

    /**
     * Creates the points.
     *
     * @param distributionOptions Distribution options
     * @return the points
     */
    private static int[] createPoints(DiscreteDistributionOptions distributionOptions) {
        if (distributionOptions.x != null) {
            return distributionOptions.x;
        }
        if (distributionOptions.inputFile != null) {
            return readIntPoints(distributionOptions.inputFile);
        }
        return series(distributionOptions.min, distributionOptions.max, distributionOptions.increment);
    }

    /**
     * Creates the points.
     *
     * @param distributionOptions Distribution options
     * @return the points
     */
    private static double[] createPoints(InverseDiscreteDistributionOptions distributionOptions) {
        if (distributionOptions.x != null) {
            return distributionOptions.x;
        }
        if (distributionOptions.inputFile != null) {
            return readDoublePoints(distributionOptions.inputFile);
        }
        return enumerate(distributionOptions.min, distributionOptions.max, distributionOptions.steps);
    }

    /**
     * Read the {@code double} valued points from the input file.
     *
     * @param inputFile the input file
     * @return the points
     */
    private static double[] readDoublePoints(File inputFile) {
        double[] points = new double[10];
        int size = 0;
        try (BufferedReader in = Files.newBufferedReader(inputFile.toPath())) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                final double x = Double.parseDouble(line);
                if (points.length == size) {
                    points = Arrays.copyOf(points, size * 2);
                }
                points[size++] = x;
            }
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        } catch (final NumberFormatException e) {
            throw new RuntimeException("Input file should contain a real number on each line", e);
        }
        return Arrays.copyOf(points, size);
    }

    /**
     * Read the {@code int} valued points from the input file.
     *
     * @param inputFile the input file
     * @return the points
     */
    private static int[] readIntPoints(File inputFile) {
        int[] points = new int[10];
        int size = 0;
        try (BufferedReader in = Files.newBufferedReader(inputFile.toPath())) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                final int x = Integer.parseInt(line);
                if (points.length == size) {
                    points = Arrays.copyOf(points, size * 2);
                }
                points[size++] = x;
            }
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        } catch (final NumberFormatException e) {
            throw new RuntimeException("Input file should contain an integer on each line", e);
        }
        return Arrays.copyOf(points, size);
    }

    /**
     * Enumerate from min to max using the specified number of steps.
     *
     * @param min the min
     * @param max the max
     * @param steps the steps
     * @return the enumeration
     */
    private static double[] enumerate(double min, double max, int steps) {
        if (!Double.isFinite(min)) {
            throw new IllegalArgumentException("Invalid minimum: " + min);
        }
        if (!Double.isFinite(max)) {
            throw new IllegalArgumentException("Invalid maximum: " + max);
        }
        if (min == max) {
            return new double[] { min };
        }
        final double[] x = new double[steps + 1];
        final double dx = (max - min) / steps;
        for (int i = 0; i < steps; i++) {
            x[i] = min + i * dx;
        }
        x[steps] = max;
        return x;
    }

    /**
     * Enumerate from min to max using the specified increment.
     *
     * @param min the min
     * @param max the max
     * @param increment the increment
     * @return the series
     */
    private static int[] series(int min, int max, int increment) {
        if (min == max) {
            return new int[] { min };
        }
        final int steps = (int) Math.ceil((double) (max - min) / increment);
        final int[] x = new int[steps + 1];
        for (int i = 0; i < steps; i++) {
            x[i] = min + i * increment;
        }
        x[steps] = max;
        return x;
    }

    /**
     * Validate the parameter array lengths are either 1 or n. Returns the maximum length (n).
     *
     * <p>Note: It is assumed lengths of 1 can be expanded to length n using an array fill
     * operation.
     *
     * @param lengths the lengths
     * @return n
     * @throws IllegalArgumentException If a length is between 1 and n.
     */
    static int validateLengths(int... lengths) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Expand the array to the specified length. The array is filled with the value at
     * index zero if a new array is created.
     *
     *  <p>Returns the original array if it is the correct length.
     *
     * @param array Array
     * @param n Length
     * @return expanded array
     */
    static double[] expandToLength(double[] array, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Expand the array to the specified length. The array is filled with the value at
     * index zero if a new array is created.
     *
     *  <p>Returns the original array if it is the correct length.
     *
     * @param array Array
     * @param n Length
     * @return expanded array
     */
    static int[] expandToLength(int[] array, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the value is close to an integer.
     *
     * @param p Value
     * @return true if within a tolerance of an integer
     */
    private static boolean closeToInteger(double p) {
        return Math.abs(Math.rint(p) - p) < DELTA_P;
    }
}
