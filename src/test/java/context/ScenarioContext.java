package context;

import models.PostEntity;
import models.UserEntity;
import models.response.CommonResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.restassured.response.Response;

public class ScenarioContext {

    // ThreadLocal instance for per-thread context management.
    private static final ThreadLocal<DataHolder> context = ThreadLocal.withInitial(DataHolder::new);

    private ScenarioContext() {
        // private constructor to prevent instantiation
    }

    public static void set(String key, Object value) {
        context.get().getDataMap().put(key, value);
    }

    public static <T> Optional<T> get(String key) {
        return Optional.ofNullable((T) context.get().getDataMap().get(key));
    }

    public static String getString(String key) {
        return (String) (get(key).orElseThrow(() -> new RuntimeException(String.format("No %s found", key))));
    }

    /**
     * Clears the context data for the current thread.
     */
    public static void clear() {
        context.get().clear();
    }

    public static void setResponse(Response response) {
        context.get().setRestResponse(response);
    }

    public static Response getResponse() {
        return context.get().getRestResponse();
    }

    public static void setCommonResponse(CommonResponse response) {
        context.get().setCommonResponse(response);
    }

    public static CommonResponse getCommonResponse() {
        return context.get().getCommonResponse();
    }

    public static void setUser(UserEntity user) {
        context.get().setUser(user);
    }

    public static UserEntity getUser() {
        return context.get().getUser();
    }

    public static void setPost(PostEntity post) {
        context.get().setPost(post);
    }
    public static PostEntity getPost() {
        return context.get().getPost();
    }

    public static void setDataRows(List<Map<String, String>> rows) {
        context.get().setRows(rows);
    }

    public static List<Map<String, String>> getDataRows() {
        return context.get().getRows();
    }
}
