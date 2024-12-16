package web.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class BasePage extends Page {

    private static String parentWindowHandle = null;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    /**
     * @param locator
     * @return
     */
    @Override
    public WebElement getElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (RuntimeException e) {
            System.out.println("Exception occured while getting an element: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param locator
     * @return
     */
    @Override
    public List<WebElement> getElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (RuntimeException e) {
            System.out.println("Exception occured while getting an element: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }


    /**
     * @param locator
     * @return
     */
    @Override
    public String getPageHeader(By locator) {
        return getElement(locator).getText();
    }

    /**
     * @param locator
     */
    @Override
    public void waitForPresenceOfElement(By locator) {
        try {
          wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Exception occurred while waiting for presence of element: " + e.getMessage());
        }
    }

    /**
     * @param locator
     */
    @Override
    public void waitForVisibiltyOfElement(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Exception occurred while waiting for visibility of element: " + e.getMessage());
        }
    }

    @Override
    public void waitForPageLoadTimeout() {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        } catch (Exception e) {
            System.out.println("Exception occurred while waiting for page to load: " + e.getMessage());
        }
    }

    /**
     * @param titleText
     */
    @Override
    public void waitForPageTitleContains(String titleText) {
        try {
            wait.until(ExpectedConditions.titleContains(titleText));
        } catch (Exception e) {
            System.out.println("Exception occurred while waiting for page title: " + e.getMessage());
        }
    }

    /**
     *
     */
    @Override
    public void switchToDefaultWindow() {
        driver.switchTo().window(parentWindowHandle);
        System.out.println("Switched to Default window");
    }

    /**
     *
     */
    @Override
    public void switchToNewWindow() {
        parentWindowHandle = driver.getWindowHandle();
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        System.out.println("Switched to new window");
    }
}
