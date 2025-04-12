package com.dmytrohont.test.steps;

import static com.dmytrohont.test.utils.ResponseUtil.getResponseAsList;
import static com.dmytrohont.test.utils.ResponseUtil.getValueForTheField;
import static com.dmytrohont.test.utils.assertions.ResponseChecker.checkSuccessResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dmytrohont.test.client.GenericRestClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.response.CommonResponse;

import java.util.function.Function;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * The type Common com.dmytrohont.test.steps.
 */
public class CommonSteps {

    private final GenericRestClient restClient = new GenericRestClient();

    @When("I send a GET request to {string} with the Id {int}")
    public void iSendGetRequestWithId(String path, Integer id) {
        Response response = restClient.get(path, id);
        ScenarioContext.setResponse(response);
    }

    @When("I send a GET request to {string}")
    public void iSendGetRequest(String path) {
        Response response = restClient.get(path);
        ScenarioContext.setResponse(response);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(Integer expectedStatus) {
        Integer actualStatus = ScenarioContext.getResponse().getStatusCode();
        assertEquals(expectedStatus, actualStatus,
                String.format("Expected status: %s, actual status: %s", expectedStatus, actualStatus));
    }

    @Then("the response should contain field {string} with the integer value {int}")
    public void theResponseShouldContainFieldWithValue(String fieldName, Integer value) {
        var actualValue = ScenarioContext.getResponse().getBody().jsonPath().get(fieldName);

        assertEquals(value, actualValue,
                String.format("For the field: %s, Expected value: %s, actual value: %s", fieldName, value, actualValue));
    }

    @Then("the response should contain a value for the field {string}")
    public void theResponseShouldContainAValueForTheField(String fieldName) {
        var actualValue = getValueForTheField(fieldName, ScenarioContext.getResponse());
        assertNotNull(actualValue, "Actual value for the field should not be null");
    }

    @Then("the response should contain not empty list of entities")
    public void theResponseShouldContainMultipleEntities() {
        var actualList = getResponseAsList(ScenarioContext.getResponse());
        assertFalse(actualList.isEmpty(), "Actual number of entities in the response is 0");
    }


    public static void iReceiveTheSuccessResponseWithIdValue(Function<CommonResponse, Integer> idProviderFunction) {
        CommonResponse response = ScenarioContext.getCommonResponse();

        checkSuccessResponse(response);
        // if the user was created successfully, the userId will be greater than 0
        long id = idProviderFunction.apply(response);
        assertNotEquals(0, id, "Entity was not created successfully. Id in the response is 0");
    }
}
