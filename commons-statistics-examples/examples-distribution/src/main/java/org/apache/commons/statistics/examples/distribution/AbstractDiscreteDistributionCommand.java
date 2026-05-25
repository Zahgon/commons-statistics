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

import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.statistics.distribution.DiscreteDistribution;
import picocli.CommandLine.Mixin;

/**
 * Base command for a discrete distribution.
 *
 * <p>Sub-classes are assumed to have a name that corresponds to the command to action.
 * Typically this is a function value in the {@link DistributionFunction} enum. The
 * distribution will be evaluated for this function using the points defined by the
 * {@link DistributionOptions}.
 *
 * <p>Alternatively the sub-class name may indicate a special command to execute.
 *
 * <p>Sub-classes must provide the list of distributions to evaluate and options for the
 * evaluation.
 */
abstract class AbstractDiscreteDistributionCommand implements Callable<Void> {

    /**
     * The standard options.
     */
    @Mixin
    private StandardOptions standardOptions;

    @Override
    public Void call() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the distributions to evaluate.
     *
     * @return the distributions
     */
    protected abstract List<Distribution<DiscreteDistribution>> getDistributions();

    /**
     * Gets the distribution options.
     * This will define the points to evaluate, and the output options.
     *
     * @return the distribution options
     */
    protected abstract DistributionOptions getDistributionOptions();
}
