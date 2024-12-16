package api.coindesk;

import api.servicehelpers.BpiService;
import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;

public class BpiCurrentPriceTests extends BaseTest {


    @Test
    public void verifyBpiCurrentPriceResponse() {
        Response response = BpiService.getCurrentPrice();
        JsonPath jsonPath = new JsonPath(response.asString());
        LinkedHashMap<String, Object> bpiMap = jsonPath.get("bpi");
        Assert.assertTrue(bpiMap.containsKey("USD"));
        Assert.assertTrue(bpiMap.containsKey("GBP"));
        Assert.assertTrue(bpiMap.containsKey("EUR"));
        String gbpDescription = jsonPath.get("bpi.GBP.description");
        Assert.assertEquals(gbpDescription, "British Pound Sterling");
        System.out.println("Verified current price response successfully!");
    }
}
