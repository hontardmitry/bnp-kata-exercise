package com.dmytrohont.test.steps;

import static com.dmytrohont.test.steps.CommonSteps.theSuccessResponseContainsIdValue;

import com.dmytrohont.test.client.PostClient;
import com.dmytrohont.test.context.ScenarioContext;
import com.dmytrohont.test.models.PostEntity;
import com.dmytrohont.test.models.response.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostSteps {

    private static final String POST_ID = "postId";
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
        var response = ScenarioContext.getCommonResponse();
        ScenarioContext.set(POST_ID, response.getPostId());
    }

    @When("I retrieve a post by stored id")
    public void iRetrieveAPostByStoredId() {
        var postId = ScenarioContext.getInteger(POST_ID);
        var post = postClient.getPostById(postId);
        ScenarioContext.setPost(post);
    }

    @Then("the retrieved post has the same title and content as the request body")
    public void theRetrievedPostHasTheSameTitleAndContentAsTheRequestBody() {
        var post = ScenarioContext.getPost();
        ObjectMapper mapper = new ObjectMapper();
        var expectedPostEntity = mapper.convertValue(ScenarioContext.getRequestBody(), PostEntity.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(post.getTitle()).isEqualTo(expectedPostEntity.getTitle());
            softly.assertThat(post.getContent()).isEqualTo(expectedPostEntity.getContent());
        });
    }
}
