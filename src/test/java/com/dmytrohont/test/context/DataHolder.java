package com.dmytrohont.test.context;

import com.dmytrohont.test.models.PostEntity;
import com.dmytrohont.test.models.UserEntity;
import com.dmytrohont.test.models.response.CommonResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.response.Response;

@Data
public class DataHolder {

    private Response restResponse;
    private CommonResponse commonResponse;
    private UserEntity user;
    private PostEntity post;
    private List<Map<String, String>> rows;
    private Map<String, Object> dataMap;

    public DataHolder() {
        this.dataMap = new HashMap<>();
        this.rows = new ArrayList<>();
    }
}
