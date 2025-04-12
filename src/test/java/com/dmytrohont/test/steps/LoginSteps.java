package com.dmytrohont.test.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static com.dmytrohont.test.utils.DataTableUtil.getRowsForCurrentEnvAndStore;

import com.dmytrohont.test.client.UserClient;
import com.dmytrohont.test.context.ScenarioContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final UserClient userClient = new UserClient();

    @When("I send a POST request to Login with login {string} and password {string}")
    public void iSendAPOSTRequestToLoginWithLoginAndPassword(String login, String password) {
        var response = userClient.login(login, password);
        ScenarioContext.setCommonResponse(response);
    }

    @When("I send a POST request to Login with stored login and password")
    public void iSendAPOSTRequestToLoginWithStoredLoginAndPassword() {
        var credentials = ScenarioContext.getDataRows()
                .stream().findFirst().orElseThrow(() -> new RuntimeException("No credentials found"));
        var response = userClient.login(credentials.get("login"), credentials.get("password"));

        ScenarioContext.setCommonResponse(response);
    }

    @Then("I receive the token value in the response")
    public void iReceiveTheSuccessResponseWithTokenValue() {
        assertNotEquals(
//                "Token should not be empty",
                "",
                getStoredToken());
    }

    @Then("I receive the response with empty value for the token field")
    public void iReceiveTheSuccessResponseWithoutTokenValue() {
        assertEquals(
//                "Token should not be empty",
                "", getStoredToken());
    }

    @Given("following user credentials:")
    public void followingUserCredentials(DataTable dataTable) {
        getRowsForCurrentEnvAndStore(dataTable);
    }

    private String getStoredToken() {
        return ScenarioContext.getCommonResponse().getToken();
    }
}
