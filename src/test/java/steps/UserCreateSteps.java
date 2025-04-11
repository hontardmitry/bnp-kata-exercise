package steps;

import static org.junit.Assert.assertNotEquals;
import static utils.assertions.ResponseChecker.checkSuccessResponse;

import client.UserClient;
import context.ScenarioContext;
import models.UserEntity;
import models.response.CommonResponse;

import java.util.Random;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserCreateSteps {

    private final UserClient userClient = new UserClient();

    @Given("I have an existing user")
    public void iHaveAnExistingUser() {
        var availableUsers = userClient.getUserAdministration();
        if (availableUsers.isEmpty()) {
            var user = UserEntity.builder().login("test").password("test").build();
            userClient.createUser(user);
        }
    }

    @Given("I have a unique user generated")
    public void iHaveAUniqueUser() {
        var uniqueString = String.valueOf(System.currentTimeMillis());
        var regularString = "test" + uniqueString;
        var email = "email" + uniqueString + "@test.com";
        var role = new Random().nextInt(3) + 1;
        var user = UserEntity.builder()
                .name(regularString)
                .lastName(regularString)
                .login(regularString)
                .password(regularString)
                .email(email)
                .role(role)
                .build();

        ScenarioContext.setUser(user);
    }

    @When("I send a POST request to create a user")
    public void iSendAPOSTRequestToCreateAUser() {
        var user = ScenarioContext.getUser();
        var response = userClient.createUser(user);
        ScenarioContext.setCommonResponse(response);
    }

    @Then("I receive the success response with userId value")
    public void iReceiveTheSuccessResponseWithUserIdValue() {
        CommonResponse response = ScenarioContext.getCommonResponse();

        checkSuccessResponse(response);
        // if the user was created successfully, the userId will be greater than 0
        assertNotEquals("UserId should not be 0", 0, response.getUserId());
    }


}
