package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.HeaderMenu;
import org.fundacionjala.pivotal.pages.ToastMessage;
import org.fundacionjala.pivotal.pages.WorkSpaceNew;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * Assertion on common steps with soft assert.
 */
public class AssertSteps {

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private WorkSpaceNew workSpaceNew;

    @Autowired
    private Header header;

    @Autowired
    private HeaderMenu headerMenu;

    @Autowired
    private ToastMessage toastMessage;

    private static final String WORKSPACE = "Workspaces";

    /**
     * This steps verify that workspace.
     *
     * @param name param.
     **/
    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        final String actualresult = this.workSpaceNew.getWorkSpaceLabel();
        Assert.assertEquals(actualresult, name);
    }

    /**
     * This step verify that workspace is displayed.
     **/
    @And("the workspace board should be displayed")
    public static void theWorkspaceBoardShouldBeDisplayed() {
        Assert.assertTrue(true);
    }

    /**
     * Validate title match on header title.
     *
     * @param headerTitle String
     */
    @Then("validates {string} on header menu")
    public void validatesOnHeaderTitle(final String headerTitle) {
        final String name = ScenarioContext.getContextAsString(headerTitle);

        final String actual = this.header.getTitleName();
        Assert.assertEquals(actual, name, String.format(" %s match on header menu", name));
    }

    /**
     * Validate title match on workspace header title.
     *
     * @param headerWSTitle String
     */
    @Then("validates {string} on workspace header title")
    public void validatesOnWSHeaderTitle(final String headerWSTitle) {
        final String name = ScenarioContext.getContextAsString(headerWSTitle);

        final String actual = this.header.getTitleWSName();
        Assert.assertEquals(actual, name, String.format(" %s match on workspace header title", name));
    }

    /**
     * Validate listing of item on a specific group.
     *
     * @param key           String
     * @param specificGroup String
     */
    @And("validates {string} on {string} group list")
    public void validatesOnGroupList(final String key, final String specificGroup) {
        final String name = ScenarioContext.getContextAsString(key);
        if (specificGroup.equals(WORKSPACE)) {
            Assert.assertTrue(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        } else {
            Assert.assertTrue(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        }
    }

    /**
     * Validate listing of item on a specific dashboard tab.
     *
     * @param key          String
     * @param specificList String
     */
    @And("validates {string} on {string} dashboard tab")
    public void validatesOnDashboardTab(final String key, final String specificList) {
        final String name = ScenarioContext.getContextAsString(key);
        if (specificList.equals(WORKSPACE)) {
            Assert.assertTrue(this.dashboard.existWorkSpace(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        } else {
            Assert.assertTrue(this.dashboard.existProject(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        }
    }

    /**
     * Validate not listing of item on a specific group.
     *
     * @param key           String
     * @param specificGroup String
     */
    @And("validates {string} not listed on {string} group list")
    public void validatesNotListedOnGroupList(final String key, final String specificGroup) {
        final String name = StringUtil.getValue(key);
        if (specificGroup.equals(WORKSPACE)) {
            Assert.assertFalse(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s not listed on %s group", name, specificGroup));
        } else {
            Assert.assertFalse(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s not listed on %s group", name, specificGroup));
        }
    }

    /**
     * Validate not listing of item on a specific dashboard tab.
     *
     * @param key          String
     * @param specificList String
     */
    @And("validates {string} not listed on {string} dashboard tab")
    public void validatesNotListedOnDashboardTab(final String key, final String specificList) {
        final String name = StringUtil.getValue(key);

        if (specificList.equals(WORKSPACE)) {
            Assert.assertFalse(this.dashboard.existWorkSpace(name),
                    String.format(" %s not listed on %s dashboard tab", name, specificList));
        } else {
            Assert.assertFalse(this.dashboard.existProject(name),
                    String.format(" %s not listed on %s dashboard tab", name, specificList));
        }
    }

    /**
     * Validate the display of a given message.
     *
     * @param messageOnScreen String
     */
    @Then("a {string} message should be displayed")
    public void aMessageShouldBeDisplayed(final String messageOnScreen) {
        Assert.assertTrue(this.toastMessage.checkVisibilityOfMessage(messageOnScreen));
    }

    /**
     * Validate the display of a given message.
     *
     * @param key             String
     * @param messageOnScreen String
     */
    @Then("a {string} {string} message should be displayed")
    public void aPersonalizedMessageShouldBeDisplayed(final String key, final String messageOnScreen) {
        final String name = StringUtil.getValue(key);
        Assert.assertTrue(this.toastMessage.checkVisibilityOfMessage(String.format("%s %s", name, messageOnScreen)));
    }
}
