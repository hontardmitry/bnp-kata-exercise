package com.dmytrohont.test.steps;

import static com.dmytrohont.test.steps.CommonSteps.theSuccessResponseContainsIdValue;

import com.dmytrohont.test.client.UserClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.UserEntity;
import com.dmytrohont.test.models.response.CommonResponse;

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

    @Then("the success response contains userId value")
    public void theSuccessResponseContainsUserIdValue() {
        theSuccessResponseContainsIdValue(CommonResponse::getUserId);
    }
}
