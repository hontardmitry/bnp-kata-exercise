package com.dmytrohont.test.client;

import static com.dmytrohont.test.utils.ResponseUtil.checkOkStatusCodeAndExtractResponse;

import com.dmytrohont.test.client.http.method.GetRequest;
import com.dmytrohont.test.client.http.method.PostRequest;
import com.dmytrohont.test.models.PostEntity;
import com.dmytrohont.test.models.response.CommonResponse;

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
