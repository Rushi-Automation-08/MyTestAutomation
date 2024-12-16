package web.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class HomePage extends BasePage{

    private final By searchBox = By.id("gh-ac-box");
    private final By searchInputBox = By.id("gh-ac");
    private final By searchButton = By.id("gh-btn");
    private final By pageHeader = By.xpath("//div[@id='mainContent']//h1");
    private final By resultCount = By.xpath("//div[@id='mainContent']//h1/span[1]");
    private final By resultList = By.xpath("//ul[contains(@class, 'srp-results srp-list')]//div[contains(@class, 's-item__title')]");
    public static String itemName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchItemCategory(String categoryToSearch) {
        waitForPageTitleContains("Electronics, Cars, Fashion, Collectibles & More | eBay");
        waitForPresenceOfElement(searchBox);
        getElement(searchBox).click();
        waitForPresenceOfElement(searchInputBox);
        getElement(searchInputBox).clear();
        getElement(searchInputBox).sendKeys(categoryToSearch);
        getElement(searchButton).click();
        waitForPageLoadTimeout();
        waitForPageTitleContains("Book for sale | eBay");
        waitForPresenceOfElement(pageHeader);
    }

    public void clickOnTheFirstItemInSearchResult() {
        if(checkIfSearchResultIsNonZero()) {
            WebElement firstItem = getElements(resultList).get(0);
            itemName = firstItem.getText();
            firstItem.click();
            switchToNewWindow();
            waitForPageLoadTimeout();
            waitForPageTitleContains(itemName + " | eBay");
        }
    }

    private boolean checkIfSearchResultIsNonZero() {
        return !getElement(resultCount).getText().equals("0");
    }
}
