package org.fundacionjala.core.ui;

import org.fundacionjala.core.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Parent of Page Objects.
 */
public abstract class AbstractPage {

    public static final long WAIT_TIME = 60;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverAction action;
    @FindBy(css = "#modal_area .tc_scrim.tc_scrim_dark")
    protected WebElement backDrop;

    /**
     * Constructor of class.
     */
    protected AbstractPage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = DriverManager.getInstance().getWait();
        this.action = new WebDriverAction(this.driver, this.wait);
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Get parent element.
     *
     * @param element web element.
     * @return parent web element.
     */
    public WebElement getParent(final WebElement element) {
        return element.findElement(By.xpath("./.."));
    }
}
