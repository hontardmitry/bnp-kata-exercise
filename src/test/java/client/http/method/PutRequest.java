package client.http.method;

import static config.RestConfig.getRequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface PutRequest {

    default Response put(String path, Object requestBody, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .put(path);
    }

    default Response put(String path, Object requestBody) {
        return put(path, requestBody, getRequestSpec());
    }
}
