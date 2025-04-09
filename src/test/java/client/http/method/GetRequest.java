package client.http.method;

import static config.RestConfig.getRequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface GetRequest {

    default Response get(RequestSpecification requestSpecification, String path, Object... pathParams) {
        return given()
                .spec(requestSpecification)
                .get(path, pathParams);
    }

    default Response get(String path, Object... pathParams) {
        return get(getRequestSpec(), path, pathParams);
    }
}
