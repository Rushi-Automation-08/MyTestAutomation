package web.ebay;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.pageobjects.HomePage;
import web.pageobjects.ItemListingPage;

public class CartTests extends BaseTest {

    @Test
    public void verifyItemsCanBeAddedToCart() {
        HomePage homePage = page.getInstance(HomePage.class);
        ItemListingPage itemListingPage = page.getInstance(ItemListingPage.class);
        homePage.searchItemCategory("book");
        homePage.clickOnTheFirstItemInSearchResult();
        itemListingPage.addToCart();
        Assert.assertEquals(itemListingPage.getItemNameFromCartBucketSummaryInfo(), HomePage.itemName);
        Assert.assertEquals(itemListingPage.getQuantityOfItemSelectedIntoQtyDpDown(),"1");
        Assert.assertEquals(itemListingPage.getQuantityOfItemIntoMiniCart(), "1");
        System.out.println("Verified the item can be added to cart successfully!");
    }
}
