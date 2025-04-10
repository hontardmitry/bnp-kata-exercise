package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import client.UserClient;
import context.ScenarioContext;
import models.response.CommonResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private static final String LOGIN_RESPONSE = "loginResponse";
    private final UserClient userClient = new UserClient();

    @When("I send a POST request to Login with login {string} and password {string}")
    public void iSendAPOSTRequestToLoginWithLoginAndPassword(String login, String password) {
        var response = userClient.login(login, password);
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
}
