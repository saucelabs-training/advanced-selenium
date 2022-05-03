// Copied from: https://github.com/primefaces/primefaces/blob/bbd83b5/primefaces-integration-tests/src/test/java/org/primefaces/integrationtests/JUnit5Selenium4Strategy.java

package com.saucelabs.advancedselenium.saucedemo;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class JUnit5Selenium4Strategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {
    @Override
    public int getParallelism() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public int getMinimumRunnable() {
        return 0;
    }

    @Override
    public int getMaxPoolSize() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public int getCorePoolSize() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public int getKeepAliveSeconds() {
        return 60;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
        return this;
    }
}
