package api;

import common.CommonUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseAPI {

    private static RequestSpecification request;

    public static RequestSpecification getRequestSpecBuilder() {
        if(request == null) {
            request = new RequestSpecBuilder().setBaseUri(getBaseURI()).build();
        }
        return request;
    }

    public static Response httpGet(Enum<?> resource) {
        return given().spec(getRequestSpecBuilder())
                .when()
                .get(resource.toString())
                .then()
                .extract().response();
    }

    private static String getBaseURI() {
        return CommonUtils.environmentAttributes.get("BaseURI");
    }
}
