package com.dmytrohont.test.client.http.method;

import static com.dmytrohont.test.config.RestConfig.getRequestSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface PostRequest {

    default Response post(String path, Object requestBody, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(path);
    }

    default Response post(String path, Object requestBody) {
        return post(path, requestBody, getRequestSpec());
    }
}
