package com.demo.springpoc.systest.cucumber;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@SuiteClasses({LoginFeatureTest.class})
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, features = {"classpath:features/login.feature"})

public class RunTest {
}