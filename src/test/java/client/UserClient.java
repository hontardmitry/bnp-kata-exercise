package client;

import static utils.ResponseUtil.checkOkStatusCodeAndExtractResponse;

import client.http.method.GetRequest;
import client.http.method.PostRequest;
import client.http.method.PutRequest;
import models.UserEntity;
import models.response.CommonResponse;

import java.util.List;

public class UserClient implements GetRequest, PostRequest, PutRequest {

    private static final String TOKEN_KEY = "token";
    private static final String USER_ENDPOINT = "User/";
    private static final String USER_ID_PATH = USER_ENDPOINT + "{id}";
    private static final String USER_LOGIN_PATH = USER_ENDPOINT + "Login";
    private static final String GET_USER_ADMINISTRATION_PATH = USER_ENDPOINT + "GetUserAdministration";
    private static final String UPDATE_USER_ROLE_PATH = USER_ENDPOINT + "UpdateUserRole";

    public CommonResponse createUser(UserEntity user) {
        return checkOkStatusCodeAndExtractResponse(post(USER_ENDPOINT, user))
                .as(CommonResponse.class);
    }

    public String loginAndGetToken(String login, String password) {
        var credentials = UserEntity.builder().login(login).password(password).build();
        return checkOkStatusCodeAndExtractResponse(post(USER_LOGIN_PATH, credentials))
                .jsonPath()
                .getString(TOKEN_KEY);
    }

    public CommonResponse login(String login, String password) {
        var credentials = UserEntity.builder().login(login).password(password).build();
        return checkOkStatusCodeAndExtractResponse(post(USER_LOGIN_PATH, credentials))
                .as(CommonResponse.class);
    }

    public UserEntity getUser(Integer id) {
        return checkOkStatusCodeAndExtractResponse(get(USER_ID_PATH, id))
                .as(UserEntity.class);
    }

    public List<UserEntity> getUserAdministration() {
        return checkOkStatusCodeAndExtractResponse(get(GET_USER_ADMINISTRATION_PATH))
                .jsonPath().getList("", UserEntity.class);
    }

    public CommonResponse updateUserRole(Integer id, short role) {
        var user = UserEntity.builder().id(id).role(role).build();
        return checkOkStatusCodeAndExtractResponse(put(UPDATE_USER_ROLE_PATH, user))
                .as(CommonResponse.class);
    }


}
