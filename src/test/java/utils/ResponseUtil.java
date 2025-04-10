package utils;

import static org.apache.http.HttpStatus.SC_OK;

import java.util.LinkedHashMap;
import java.util.List;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static List<LinkedHashMap<String, Object>> getResponseAsList(Response response) {
        if (response != null) {
            return response.as(List.class);
        } else {
            throw new IllegalArgumentException(
                    String.format("The response was not provided for the '%s' method.",
                            new Exception().getStackTrace()[0].getMethodName()));
        }
    }

    public static Object getValueForTheField(String fieldName, Response response) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }
        try {
            return response
                    .getBody()
                    .jsonPath()
                    .get(fieldName);
        } catch (Exception e) {
            return null; // Field not found or other error
        }
    }

    public static ExtractableResponse<Response> checkStatusCodeAndExtractResponse(Response response, Integer expectedStatusCode) {
        return response.then().statusCode(expectedStatusCode).extract();

    }

    public static ExtractableResponse<Response> checkOkStatusCodeAndExtractResponse(Response response) {
        return response.then().statusCode(SC_OK).extract();

    }
}
