package com.dmytrohont.test.steps;

import static com.dmytrohont.test.client.UserClient.USER_ID_PATH;
import static com.dmytrohont.test.steps.CommonSteps.theSuccessResponseContainsIdValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.dmytrohont.test.client.UserClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.UserEntity;
import com.dmytrohont.test.models.response.CommonResponse;

import java.util.List;
import java.util.Random;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {

    private static final String USER_ID = "userId";
    private static final String USER_ADMINISTRATION = "userAdministration";
    private final UserClient userClient = new UserClient();

    @When("I send a GET request to the 'User' endpoint with the Id {int}")
    public void iSendGetRequestToGetUserById(Integer id) {
        var response = userClient.getUserAsResponse(id);
        ScenarioContext.setResponse(response);
    }

    @When("I send a GET request to the 'User' endpoint with invalid Id {int}")
    public void iSendGetRequestToGetUserByInvalidId(Integer id) {
        var response = userClient.get(USER_ID_PATH, id);
        ScenarioContext.setResponse(response);
    }

    @When("I send a GET request to the 'User' endpoint without Id")
    public void iSendGetRequestToGetUserByInvalidId() {
        var response = userClient.get(USER_ID_PATH);
        ScenarioContext.setResponse(response);
    }

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
        theSuccessResponseContainsIdValue(CommonResponse::getUserId, USER_ID);
    }

    @When("I send a GET request to the 'GetUserAdministration' endpoint")
    public void iSendGetRequestToGetUserAdministration() {
        var posts = userClient.getUserAdministration();
        ScenarioContext.set(USER_ADMINISTRATION, posts);
    }

    @Then("the response contains a list of users with not empty values for fields: id, role, email, name, lastName")
    public void theResponseContainsAListOfUsersWithValues() {
        List<UserEntity> posts = ScenarioContext.get(USER_ADMINISTRATION, List.class);
        posts.forEach(this::checkEachUserHasValuesForRequiredFields);
    }

    @Then("the response contains a list of users without value for the login and password fields")
    public void theResponseContainsAListOfUsersWithoutPassword() {
        List<UserEntity> posts = ScenarioContext.get(USER_ADMINISTRATION, List.class);
        posts.forEach(user -> {
            assertNull(user.getPassword(),
                    "The password should be null");
            assertNull(user.getLogin(),
                    "The login should be null");
        });
    }

    @When("I send a PUT request to update a user role with id {int} and role {int}")
    public void iSendAPUTRequestToUpdateUserRole(Integer id, Integer role) {
        var response = userClient.updateUserRole(id, role);
        ScenarioContext.setResponse(response);
    }

    private void checkEachUserHasValuesForRequiredFields(UserEntity user) {
        assertNotNull(user);
        assertNotEquals(0, user.getId(), "Id should not be 0");
        assertNotEquals(0, user.getRole(), "Role should not be 0");
        assertFalse(user.getEmail().isBlank(), "Email should be blank");
        assertFalse(user.getName().isBlank(), "Name should be blank");
        assertFalse(user.getLastName().isBlank(), "LastName should be blank");
    }
}
