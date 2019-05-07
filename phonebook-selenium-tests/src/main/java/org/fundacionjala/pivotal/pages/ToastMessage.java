package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

/**
 * Represent all message toast that appear when an action was taken.
 */
@Component
public class ToastMessage extends AbstractPage {

    /**
     * Method to check the visibility of a message.
     *
     * @param message String
     * @return boolean
     */
    public boolean checkVisibilityOfMessage(final String message) {
        final WebElement textOnScreen = driver.findElement(By.xpath("//*[text()=\"" + message + "\"]"));
        return textOnScreen.getAttribute("innerHTML").equals(message);
    }

}
