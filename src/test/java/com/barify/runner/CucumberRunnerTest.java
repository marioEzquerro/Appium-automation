package com.barify.runner;

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
                "com.barify.stepdefs",
                "com.barify.support"
        },
        features = {
                "src/test/resources/features/"
        }
)
public class CucumberRunnerTest {
}
