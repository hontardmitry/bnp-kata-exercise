package com.dmytrohont.test.steps;

import static com.dmytrohont.test.utils.DataTableUtil.getRowsForCurrentEnvAndStore;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dmytrohont.test.client.UserClient;
import com.dmytrohont.test.context.ScenarioContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final UserClient userClient = new UserClient();

    @When("I send a POST request to the 'Login' endpoint with login {string} and password {string}")
    public void iSendAPOSTRequestToLoginWithLoginAndPassword(String login, String password) {
        var response = userClient.login(login, password);
        ScenarioContext.setCommonResponse(response);
    }

    @When("I send a POST request to the 'Login' endpoint with stored login and password")
    public void iSendAPOSTRequestToLoginWithStoredLoginAndPassword() {
        var credentials = ScenarioContext.getDataRows()
                .stream().findFirst().orElseThrow(() -> new RuntimeException("No credentials found"));
        var response = userClient.login(credentials.get("login"), credentials.get("password"));

        ScenarioContext.setCommonResponse(response);
    }

    @Then("the response contains the token value")
    public void theResponseContainsTheTokenValue() {
        assertFalse(getStoredToken().isBlank(), "Token should not be blank");
    }

    @Then("the token value is blank in the response")
    public void theTokenValueIsBlankInTheResponse() {
        var actualToken = getStoredToken();
        assertTrue(actualToken.isBlank(), String.format("Actual token value '%s' is not blank", actualToken));
    }

    @Given("following user credentials:")
    public void followingUserCredentials(DataTable dataTable) {
        getRowsForCurrentEnvAndStore(dataTable);
    }

    private String getStoredToken() {
        return ScenarioContext.getCommonResponse().getToken();
    }
}
