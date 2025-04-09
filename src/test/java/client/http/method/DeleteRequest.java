package client.http.method;

import static config.RestConfig.getRequestSpec;
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
