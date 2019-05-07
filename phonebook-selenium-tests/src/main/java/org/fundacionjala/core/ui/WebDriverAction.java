package org.fundacionjala.core.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class with common actions to execute.
 */
public class WebDriverAction {
    private static final Logger LOGGER = LogManager.getLogger(WebDriverAction.class.getName());

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Constructor.
     *
     * @param driver WebDriver
     * @param wait   WebDriverWait
     */
    public WebDriverAction(final WebDriver driver, final WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    /**
     * wait and set a value.
     *
     * @param element webElement
     * @param value   text
     */
    public void setValue(final WebElement element, final String value) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Scroll to web element.
     *
     * @param element to scroll to
     */
    public void scrollToElement(final WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
        final JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * javascript click.
     *
     * @param element a web elment.
     */
    public void clickJS(final WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
        final JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Click event with explicit wait for click.
     *
     * @param element to click.
     */
    public void click(final WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    /**
     * Click event with By.
     *
     * @param locator Input By.
     */
    public void click(final By locator) {
        this.wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Method for wait visibility of an element.
     *
     * @param element Input WebElement.
     */
    public void waitVisibility(final WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Method for wait visibility of an element.
     *
     * @param element Input By locator.
     */
    public void waitVisibility(final By element) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /***
     * class review.
     * @param element web element.
     */
    public void waitInvisibility(final WebElement element) {
        this.wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * element stale .
     *
     * @param element input .
     */
    public void staleElement(final WebElement element) {
        this.wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Method for wait Presence of an element.
     *
     * @param element Input By locator.
     * @return Web Element.
     */
    public WebElement waitPresenceOfElement(final By element) {
        return this.wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    /**
     * Method for return text By element.
     *
     * @param element By type.
     * @return the value of String type.
     */
    public String getValue(final By element) {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return this.driver.findElement(element).getText();
    }

    /**
     * Method for return text By element.
     *
     * @param element WebElement type.
     * @return the value of String text.
     */
    public String getValue(final WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    /**
     * Method for return attribute By element.
     *
     * @param element   By type.
     * @param attribute to search.
     * @return String.
     */
    public String getAttribute(final By element, final String attribute) {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(element));
        final WebElement webElement = this.driver.findElement(element);
        return webElement.getAttribute(attribute);
    }

    /**
     * Method for return attribute By element.
     *
     * @param element   WebElement.
     * @param attribute to search.
     * @return String.
     */
    public String getAttribute(final WebElement element, final String attribute) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    /**
     * Method for verify Exist WebElement.
     *
     * @param element By type.
     * @return boolean if exist element.
     */
    public boolean isExistingSelector(final By element) {
        try {
            this.driver.findElement(element);
        } catch (final NoSuchElementException e) {
            LOGGER.warn(String.format("Method: isExistingSelectorBy -> FALSE %s", element));
            return false;
        }
        return true;
    }

    /**
     * this element make to pause.
     */
    public void pause() {
        final int time = 500;
        pause(time);
    }

    /**
     * This element make to pause.
     *
     * @param timeOut Input time to wait.
     */
    public void pause(final int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (final InterruptedException e) {
            LOGGER.error("Error in the sleep: ", e);
            Thread.currentThread().interrupt();
        }
    }
}
