package com.dmytrohont.test.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hook {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hook.class);

    // the goal of these methods is to demonstrate the usage of hooks
    @After
    public void executeAfterScenario(Scenario scenario) {
        LOGGER.info("Scenario execution is finished for {}", scenario.getName());
    }

    @BeforeAll
    public static void executeBeforeAllScenarios() {
        LOGGER.info("All tests are running in {}", System.getProperty("env", "prod"));
    }
}
