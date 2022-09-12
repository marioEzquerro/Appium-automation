package com.saucelabs.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/surefire-reports/cucumber.json",
                "html:target/cucumber-html-report.html"
        },
        glue = {
                "com.saucelabs.stepsdef",
                "com.saucelabs.support"
        },
        features = {
                "src/test/resources/features/"
        }
)
public class CucumberRunnerTest {
}
