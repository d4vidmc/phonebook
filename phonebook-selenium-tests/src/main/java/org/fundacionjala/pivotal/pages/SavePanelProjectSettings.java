package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * this is a Save Panel located in Project Settings page.
 */
@Component
public class SavePanelProjectSettings extends AbstractPage {
    @FindBy(css = "input[class='save_bar__submit']")
    private WebElement saveButtonOnEditProject;

    /**
     * save edited fields.
     */
    public void saveFormOnEditProject() {
        action.click(saveButtonOnEditProject);
        driver.switchTo().alert().accept();
    }
}
