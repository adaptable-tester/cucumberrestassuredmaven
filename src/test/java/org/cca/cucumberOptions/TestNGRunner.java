package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
    monochrome = true,
    tags = "@smoke" // Optional: Run only scenarios tagged with @smoke
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}