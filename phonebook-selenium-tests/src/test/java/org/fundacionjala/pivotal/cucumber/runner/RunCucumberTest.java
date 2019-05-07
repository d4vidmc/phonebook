package org.fundacionjala.pivotal.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Class which runs all features.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.fundacionjala"},
        plugin = {"pretty"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    /** This method execute before the tests. */
    public void open() {
        // Implement.
        DriverManager.getInstance().getDriver().get(Environment.getInstance().getValue("url.base"));
    }

    /** This method close the browser after the features finish. */
    public void close() {
        DriverManager.getInstance().getDriver().quit();
    }
}
