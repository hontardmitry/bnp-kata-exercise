package com.dmytrohont.test.steps;

import static com.dmytrohont.test.utils.FileUtil.loadJsonData;
import static com.dmytrohont.test.utils.ResponseUtil.getResponseAsList;
import static com.dmytrohont.test.utils.ResponseUtil.getValueForTheField;
import static com.dmytrohont.test.utils.StringUtil.getStringsList;
import static com.dmytrohont.test.utils.assertions.ResponseChecker.checkSuccessResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.response.CommonResponse;

import java.io.IOException;
import java.util.function.Function;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * The type Common com.dmytrohont.test.steps.
 */
public class CommonSteps {

    public static final String ERROR_MSG_VALUES_NOT_EQUAL_TMPLT =
            "For the field: %s, Expected value: %s, actual value: %s";

    @ParameterType("true|false")
    public Boolean booleanValue(String bool) {
        return Boolean.parseBoolean(bool);
    }

    @Then("the response status is {int}")
    public void theResponseStatusShouldBe(Integer expectedStatus) {
        Integer actualStatus = ScenarioContext.getResponse().getStatusCode();
        assertEquals(expectedStatus, actualStatus,
                String.format("Expected status: %s, actual status: %s", expectedStatus, actualStatus));
    }

    @Then("the response contains field {string} with the integer value {int}")
    public void theResponseContainIntegerFieldWithValue(String fieldName, Integer value) {
        theResponseContainsFieldWithValue(fieldName, value);
    }

    @Then("the response contains field {string} with value {string}")
    public void theResponseContainsTitleFieldWithValue(String fieldName, String value) {
        theResponseContainsFieldWithValue(fieldName, value);
    }

    public <T> void theResponseContainsFieldWithValue(String fieldName, T value) {
        var actualValue = ScenarioContext.getResponse().getBody().jsonPath().get(fieldName);
        assertEquals(value, actualValue, String.format(ERROR_MSG_VALUES_NOT_EQUAL_TMPLT, fieldName, actualValue, value));
    }

    @Then("the response contains a value for the field {string}")
    public void theResponseShouldContainAValueForTheField(String fieldName) {
        var actualValue = getValueForTheField(fieldName, ScenarioContext.getResponse());
        assertNotNull(actualValue, "Actual value for the field should not be null");
    }

    @Then("the response contains not empty list of entities")
    public void theResponseShouldContainMultipleEntities() {
        var actualList = getResponseAsList(ScenarioContext.getResponse());
        assertFalse(actualList.isEmpty(), "Actual number of entities in the response is 0");
    }

    @Given("the request body is loaded from {string}")
    public void theRequestBodyIsLoadedFrom(String fileName) throws IOException {
        var jsonNode = loadJsonData(fileName);
        ScenarioContext.setRequestBody(jsonNode);
    }

    @Then("the success field in the response contains {booleanValue} value")
    public void theSuccessFieldInTheResponseContainsValue(boolean success) {
        var actualIsSuccess = ScenarioContext.getCommonResponse().isSuccess();
        assertEquals(success, actualIsSuccess,
                String.format("Actual success field value %s is not as expected %s", actualIsSuccess, success));
    }

    @Then("the validationErrors field in the response contains {string} values")
    public void theValidationErrorsFieldInTheResponseContainsValues(String validationErrorsString) {
        var actualValidationErrors = ScenarioContext.getCommonResponse().getValidationErrors();
        var validationErrors = getStringsList(validationErrorsString, ";");
        validationErrors.forEach(validationError -> {
            var actualContainsValidationError = actualValidationErrors.contains(validationError);
            assertTrue(actualContainsValidationError,
                    String.format("Actual validationErrors field value %s does not contain expected value %s",
                            actualValidationErrors, validationError));
        });
    }
    @Then("the validationErrors field in the response is empty")
    public void theValidationErrorsFieldInTheResponseIsEmpty() {
        var actualValidationErrors = ScenarioContext.getCommonResponse().getValidationErrors();
       assertTrue(actualValidationErrors.isEmpty(),
               String.format("Actual validationErrors field value %s is not empty", actualValidationErrors));
    }

    public static void theSuccessResponseContainsIdValue(Function<CommonResponse, Integer> idProviderFunction,
                                                         String key) {
        CommonResponse response = ScenarioContext.getCommonResponse();
        checkSuccessResponse(response);
        // if the user was created successfully, the userId will be greater than 0
        long id = idProviderFunction.apply(response);
        assertNotEquals(0, id, "Entity was not created successfully. Id in the response is 0");
        ScenarioContext.set(key, id);
    }
}
