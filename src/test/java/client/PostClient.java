package client;

import static utils.ResponseUtil.checkOkStatusCodeAndExtractResponse;

import client.http.method.GetRequest;
import client.http.method.PostRequest;
import models.PostEntity;
import models.response.CommonResponse;

public class PostClient implements GetRequest, PostRequest {

    private static final String POSTS_ENDPOINT = "Post/";

    public CommonResponse createPost(String title, String content) {
        var postBody = new PostEntity();
        postBody.setTitle(title);
        postBody.setContent(content);
        return createPost(postBody);
    }

    public CommonResponse createPost(Object postBody) {
        return checkOkStatusCodeAndExtractResponse(post(POSTS_ENDPOINT, postBody))
                .as(CommonResponse.class);
    }

    public PostEntity getPostById(int id) {
        return checkOkStatusCodeAndExtractResponse(get(POSTS_ENDPOINT + id))
                .as(PostEntity.class);
    }
}
