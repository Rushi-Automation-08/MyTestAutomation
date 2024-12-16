package web.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public abstract WebElement getElement(By locator);

    public abstract List<WebElement> getElements(By locator);

    public abstract String getPageTitle();

    public abstract String getPageHeader(By locator);

    public abstract void waitForPresenceOfElement(By locator);

    public abstract void waitForVisibiltyOfElement(By locator);

    public abstract void waitForPageLoadTimeout();

    public abstract void waitForPageTitleContains(String titleText);

    public abstract void switchToDefaultWindow();

    public abstract void switchToNewWindow();

    public <AppPage extends BasePage> AppPage getInstance(Class<AppPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (Exception e) {
            System.out.println("Exception occurred while creating instance of page: " + e.getMessage());
        }
        return null;
    }
}