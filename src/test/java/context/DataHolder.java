package context;

import lombok.Data;
import models.PostEntity;
import models.UserEntity;
import models.response.CommonResponse;

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

    // Constructor to initialize the collections.
    public DataHolder() {
        this.dataMap = new HashMap<>();
        this.rows = new ArrayList<>();
    }

    /**
     * Clears all stored data from this DataHolder, resetting the internal state.
     */
    public void clear() {
        // Clear the data map.
        dataMap.clear();

        // Reset other fields.
        restResponse = null;
        commonResponse = null;
        user = null;
        rows.clear();
    }
}
