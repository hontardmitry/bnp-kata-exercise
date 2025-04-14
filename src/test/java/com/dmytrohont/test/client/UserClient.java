package com.dmytrohont.test.client;

import static com.dmytrohont.test.utils.ResponseUtil.checkOkStatusCodeAndExtractResponse;

import com.dmytrohont.test.client.http.method.GetRequest;
import com.dmytrohont.test.client.http.method.PostRequest;
import com.dmytrohont.test.client.http.method.PutRequest;
import com.dmytrohont.test.models.UserEntity;
import com.dmytrohont.test.models.response.CommonResponse;

import java.util.List;

import io.restassured.response.Response;

public class UserClient implements GetRequest, PostRequest, PutRequest {
// Each API endpoint exposes a different set of operations. For example, retrieving an entity by ID is only supported
// by the User endpoint. Therefore, using interfaces with default methods provides more flexibility and better
// separation of concerns than a common abstract client.

    public static final String USER_ENDPOINT = "User/";
    public static final String USER_ID_PATH = USER_ENDPOINT + "{id}";
    public static final String USER_LOGIN_PATH = USER_ENDPOINT + "Login";
    public static final String GET_USER_ADMINISTRATION_PATH = USER_ENDPOINT + "GetUserAdministration";
    public static final String UPDATE_USER_ROLE_PATH = USER_ENDPOINT + "UpdateUserRole";

    public CommonResponse createUser(UserEntity user) {
        return post(USER_ENDPOINT, user)
                .as(CommonResponse.class);
    }

    public CommonResponse login(String login, String password) {
        var credentials = UserEntity.builder().login(login).password(password).build();
        return post(USER_LOGIN_PATH, credentials)
                .as(CommonResponse.class);
    }

    public Response getUserAsResponse(Integer id) {
        return get(USER_ID_PATH, id);
    }

    public List<UserEntity> getUserAdministration() {
        return checkOkStatusCodeAndExtractResponse(get(GET_USER_ADMINISTRATION_PATH))
                .jsonPath().getList("", UserEntity.class);
    }

    public Response updateUserRole(Integer id, Integer role) {
        var user = UserEntity.builder().id(id).role(role).build();
        return put(UPDATE_USER_ROLE_PATH, user);
    }
}
