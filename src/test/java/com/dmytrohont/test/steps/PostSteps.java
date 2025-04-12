package com.dmytrohont.test.steps;

import static com.dmytrohont.test.steps.CommonSteps.iReceiveTheSuccessResponseWithIdValue;
import static com.dmytrohont.test.utils.FileUtil.loadJsonData;

import com.dmytrohont.test.client.PostClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.response.CommonResponse;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostSteps {

    private static final String POST_BODY = "postBody";
    private final PostClient postClient = new PostClient();


    @Given("I have a post body from file {string}")
    public void iHaveAPostContentAndTitleFromFile(String fileName) throws IOException {
        var jsonNode = loadJsonData(fileName);
        ScenarioContext.set(POST_BODY, jsonNode);
    }

    @When("I send a POST request to create a post with stored post body")
    public void iSendAPOSTRequestToCreateAPostWithStoredTitleAndContent() {
        var postBody = ScenarioContext.getObject(POST_BODY);

        var response = postClient.createPost(postBody);
        ScenarioContext.setCommonResponse(response);
    }

    @When("I send a POST request to create a post with title {string} and content {string}")
    public void iSendAPOSTRequestToCreateAPostWithTitleAndContent(String title, String content) {
        var response = postClient.createPost(title, content);
        ScenarioContext.setCommonResponse(response);
    }

    @Then("I receive the success response with postId value")
    public void iReceiveTheSuccessResponseWithPostIdValue() {
        iReceiveTheSuccessResponseWithIdValue(CommonResponse::getPostId);
    }
}
