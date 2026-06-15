package com.porto.automation.stepdefinitions;

import com.porto.automation.utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    @Before("@UI")
    public void setUp() {
        System.out.println("LOG: Cucumber Hooks triggering Driver Initialization...");
        DriverFactory.initializeDriver();
    }

    @After("@UI")
    public void tearDown() {
        System.out.println("LOG: Cucumber Hooks triggering Driver Teardown...");
        DriverFactory.quitDriver();
    }
}
