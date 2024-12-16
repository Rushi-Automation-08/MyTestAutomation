package api.servicehelpers;

import api.BaseAPI;
import api.endpoints.CoinDeskEndpoints;
import io.restassured.response.Response;

public class BpiService extends BaseAPI {

        public static Response getCurrentPrice() {
            return httpGet(CoinDeskEndpoints.BPI_CURRENT_PRICE);
        }

}
