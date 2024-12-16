package web.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ItemListingPage extends BasePage {

    private final By addToCartButton = By.id("atcBtn_btn_1");
    private final By CART_BUCKET_SUMMARY_INFO = By.xpath("//div[@data-test-id='cart-bucket']//h3");
    private final By itemQuantityDpDown = By.xpath("//div[@class='grid-item-quantity']//select[@data-test-id='qty-dropdown']");
    private final By itemCountInSummary = By.xpath("//div[@class='cart-summary-line-item'");
    private final By GO_TO_CHECKOUT_BTN = By.xpath("//button[]@data-test-id='cta-top'");
    private final By MINI_CART_COUNT = By.cssSelector("i#gh-cart-n");



    public ItemListingPage(WebDriver driver) {
        super(driver);
    }


    public void addToCart() {
        waitForPageLoadTimeout();
        waitForVisibiltyOfElement(addToCartButton);
        getElement(addToCartButton).click();
        waitForPageLoadTimeout();
    }

    public String getItemNameFromCartBucketSummaryInfo() {
        waitForVisibiltyOfElement(CART_BUCKET_SUMMARY_INFO);
        return getElement(CART_BUCKET_SUMMARY_INFO).getText();
    }

    public String getQuantityOfItemSelectedIntoQtyDpDown() {
        waitForPageLoadTimeout();
        waitForVisibiltyOfElement(itemQuantityDpDown);
        Select select = new Select(getElement(itemQuantityDpDown));
        return select.getFirstSelectedOption().getText();
    }


    public String getQuantityOfItemIntoMiniCart() {
        waitForPageLoadTimeout();
        waitForVisibiltyOfElement(MINI_CART_COUNT);
        return getElement(MINI_CART_COUNT).getText();
    }

    public String getQuantityOfItemShownInSummaryLine() {
        waitForVisibiltyOfElement(itemCountInSummary);
        return getElement(itemCountInSummary).getText();
    }
}
