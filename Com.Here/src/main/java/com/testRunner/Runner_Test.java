package com.testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature/VerifyStatusCodeOfLinks.feature", glue= {"stepDef"})

public class Runner_Test {

}
