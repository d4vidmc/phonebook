package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * This is page for create accounts inside Project page options.
 */
@Component
public class CreateAccount extends AbstractPage {
    /**
     * this is a text fiel for the input of a new account name.
     */
    @FindBy(css = "input[class='tc-account-creator__name']")
    private WebElement newAccountNameTextField;

    /**
     * This method is used for set the text field.
     *
     * @param accountNameTextField is the new of the account.
     */
    public void setNewAccountNameTextField(final String accountNameTextField) {
        this.action.setValue(this.newAccountNameTextField,
                accountNameTextField);

    }
}
