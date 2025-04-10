package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static utils.DataTableUtil.getRowsForCurrentEnvAndStore;

import client.UserClient;
import context.ScenarioContext;
import models.response.CommonResponse;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private static final String LOGIN_RESPONSE = "loginResponse";
    private static final String CREDENTIAL_ROWS = "loginResponse";
    private final UserClient userClient = new UserClient();

    @When("I send a POST request to Login with login {string} and password {string}")
    public void iSendAPOSTRequestToLoginWithLoginAndPassword(String login, String password) {
        var response = userClient.login(login, password);
        ScenarioContext.setCommonResponse(LOGIN_RESPONSE, response);
    }

    @When("I send a POST request to Login with stored login and password")
    public void iSendAPOSTRequestToLoginWithStoredLoginAndPassword() {
        var credentials = ((List<Map<String, String>>) ScenarioContext.get(CREDENTIAL_ROWS)
                .orElseThrow(() -> new RuntimeException("Credentials not found")))
                .stream().findFirst().get();
        var response = userClient.login(credentials.get("login"), credentials.get("password"));
        ScenarioContext.setCommonResponse(LOGIN_RESPONSE, response);
    }

    @Then("I receive the token value in the response")
    public void iReceiveTheSuccessResponseWithTokenValue() {
        CommonResponse response = (CommonResponse) ScenarioContext.get(LOGIN_RESPONSE).get();
        assertNotEquals("Token should not be empty", "",response.getToken());
    }

    @Then("I receive the response with empty value for the token field")
    public void iReceiveTheSuccessResponseWithoutTokenValue() {
        CommonResponse response = (CommonResponse) ScenarioContext.get(LOGIN_RESPONSE).get();
        assertEquals("Token should not be empty", "",response.getToken());
    }

    @Given("following user credentials:")
    public void followingUserCredentials(DataTable dataTable) {
        getRowsForCurrentEnvAndStore(dataTable, CREDENTIAL_ROWS);
    }
}
