package org.fundacionjala.pivotal.pages;

        import org.fundacionjala.core.ui.AbstractPage;
        import org.fundacionjala.core.ui.forms.FormsElements;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.springframework.stereotype.Component;

        import java.util.HashMap;
        import java.util.Map;

/**
 * This is page for create accounts inside Project page options.
 */
@Component
public class Register extends AbstractPage {
    /**
     * this is a text field for the input of a new account name.
     */
    @FindBy(css = ".btn.btn-primary")
    private WebElement registerButton;
    @FindBy(css = "input[name='name']")
    private WebElement usernameField;
    @FindBy(css = "input[name='email']")
    private WebElement emailField;
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    @FindBy(css = "input[name='password_confirmation']")
    private WebElement confirmPasswordField;

    /**
     * Create new project by given name.
     *
     * @param formElements value
     */
    public void registerNewUser(final Map<String, String> formElements) {

        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.NAME.toString(),
                () -> setUserName(formElements
                        .get(FormsElements.NAME.toString())));
        strategy.put(FormsElements.EMAIL.toString(),
                () -> setEmail(formElements
                        .get(FormsElements.EMAIL.toString())));
        strategy.put(FormsElements.PASSWORD.toString(),
                () -> setPassword(formElements
                        .get(FormsElements.PASSWORD.toString())));

        formElements.keySet()
                .forEach(key -> strategy.get(key).perform());
        clickRegisterButton();
    }
    public void clickRegisterButton () {
            this.action.click(registerButton);
    }
    public void setUserName (final String userName) {
            this.action.setValue(usernameField,userName);
    }
    public void setEmail (final String email) {
            this.action.setValue(emailField,email);
    }
    public void setPassword (final String password) {
            this.action.setValue(passwordField,password);
            this.action.setValue(confirmPasswordField,password);
    }
}
