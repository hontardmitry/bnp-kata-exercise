package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static steps.ResponseUtil.getResponseAsList;
import static steps.ResponseUtil.getValueForTheField;


import client.GenericRestClient;
import context.ScenarioContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * The type Common steps.
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
        assertEquals(expectedStatus, ScenarioContext.getResponse().getStatusCode());
    }

    @Then("the response should contain field {string} with the integer value {int}")
    public void theResponseShouldContainFieldWithValue(String fieldName, Integer value) {
        var actualValue = ScenarioContext.getResponse()
                .getBody()
                .jsonPath()
                .get(fieldName);
        assertEquals(value, actualValue);
    }

    @Then("the response should contain a value for the field {string}")
    public void theResponseShouldContainAValueForTheField(String fieldName) {
        var actualValue = getValueForTheField(fieldName, ScenarioContext.getResponse());
        assertNotNull(actualValue, "Actual value for the field is null");
    }

    @Then("the response should contain not empty list of entities")
    public void theResponseShouldContainMultipleEntities() {
        var actualValue = getResponseAsList(ScenarioContext.getResponse());
        assertTrue(actualValue.size() > 1, "Actual number of entities in the response is 0");
    }
}
