package com.dmytrohont.test.client;

import com.dmytrohont.test.client.http.method.GetRequest;
import com.dmytrohont.test.client.http.method.PostRequest;
import com.dmytrohont.test.models.PostEntity;
import com.dmytrohont.test.models.response.CommonResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PostClient implements GetRequest, PostRequest {

    private static final String POSTS_ENDPOINT = "Post/";

    public CommonResponse createPost(String title, String content) {
        var postBody = new PostEntity();
        postBody.setTitle(title);
        postBody.setContent(content);
        return createPost(postBody);
    }

    public CommonResponse createPost(Object postBody) {
        return post(POSTS_ENDPOINT, postBody)
                .as(CommonResponse.class);
    }

    public List<PostEntity> getAllPosts() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = get(POSTS_ENDPOINT)
                .as(JsonNode.class);

        return mapper.convertValue(jsonNode,
                new TypeReference<List<PostEntity>>() {});

    }

    public PostEntity getPostById(int id) {
        //as far as there is no dedicated method for retrieving post by id here is a workaround used
        return getAllPosts().stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post with id " + id + " not found"));
    }
}
