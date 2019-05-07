package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.pivotal.pages.*;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.Map;

/**
 * Common steps.
 */
public class CommonSteps {

    @Autowired
    private Login login;

    @Autowired
    private Register register;

    @Autowired
    private Welcome welcome;

    private static Assertion assertion;

    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        final String userNameKey = String
                .format("credentials.%s.username", key);
        final String passwordKey = String
                .format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance()
                .getValue("url.login"));
        this.login.loginAs(Environment.getInstance().getValue(userNameKey),
                Environment.getInstance().getValue(passwordKey));
    }


    /**
     * Based on tag annotation enable soft assert.
     */
    @Before("@SoftAssert")
    public static void initialize() {
        assertion = new SoftAssert();
    }

    /**
     * Based on tag annotation enable soft assert.
     */
    @Before
    public static void initializeHardAssert() {
        assertion = new Assertion();
    }

    /**
     * Final step validation for soft assert.
     */
    @And("asserts all")
    public static void assertAll() {
        if (assertion instanceof SoftAssert) {
            ((SoftAssert) assertion).assertAll();
        }


    }

    @Given("the register form page")
    public void theRegisterFormPage() {
        this.welcome.openRegisterForm();
    }

    @When("creates a new user account as:")
    public void createsANewUserAccountAs(final Map<String, String> formAttributes) {
        ScenarioContext.getInstance().setContext(FormsElements.NAME.toString(), formAttributes
                .get(FormsElements.NAME.toString()));
        this.register.registerNewUser(formAttributes);
    }
}
