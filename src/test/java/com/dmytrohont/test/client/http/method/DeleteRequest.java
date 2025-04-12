package com.dmytrohont.test.client.http.method;

import static com.dmytrohont.test.config.RestConfig.getRequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface DeleteRequest {

    default Response delete(String path, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete(path);
    }

    default Response delete(String path) {
        return delete(path, getRequestSpec());
    }
}
