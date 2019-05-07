package org.fundacionjala.pivotal.pages;

        import org.fundacionjala.core.ui.AbstractPage;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.springframework.stereotype.Component;

/**
 * This is page for create accounts inside Project page options.
 */
@Component
public class Welcome extends AbstractPage {
    /**
     * this is a text fiel for the input of a new account name.
     */
    @FindBy(xpath = "//a[text()='Register']")
    private WebElement registerButton;

    /**
     * Open register form by clicking.
     */
    public void openRegisterForm() {
        this.action.click(registerButton);

    }
}
