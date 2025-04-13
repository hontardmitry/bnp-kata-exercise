package com.dmytrohont.test.steps;

import static com.dmytrohont.test.steps.CommonSteps.theSuccessResponseContainsIdValue;

import com.dmytrohont.test.client.PostClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.response.CommonResponse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostSteps {

    private final PostClient postClient = new PostClient();

    @When("I send a POST request to create a post with stored post body")
    public void iSendAPOSTRequestToCreateAPostWithStoredTitleAndContent() {
        var postBody = ScenarioContext.getRequestBody();

        var response = postClient.createPost(postBody);
        ScenarioContext.setCommonResponse(response);
    }

    @When("I send a POST request to create a post with title {string} and content {string}")
    public void iSendAPOSTRequestToCreateAPostWithTitleAndContent(String title, String content) {
        var response = postClient.createPost(title, content);
        ScenarioContext.setCommonResponse(response);
    }

    @Then("the success response contains postId value")
    public void theSuccessResponseContainsPostIdValue() {
        theSuccessResponseContainsIdValue(CommonResponse::getPostId);
    }
}
