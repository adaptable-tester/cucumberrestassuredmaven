package org.cca.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = {"stepDefinitions"}, // Package containing step definitions
        plugin = {
                "pretty", // Console output
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json" // JSON report
        },
        monochrome = true, // Readable console output
        tags = "" // Optional: e.g., "@smoke" to run specific scenarios
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}