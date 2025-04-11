package context;

import models.PostEntity;
import models.UserEntity;
import models.response.CommonResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import io.restassured.response.Response;

public class ScenarioContext {

    private static final String NOT_FOUND_ERROR_MESSAGE_TMPLT = "No %s found in the data holder";
    // ThreadLocal instance for per-thread context management.
    private static final ThreadLocal<DataHolder> context = ThreadLocal.withInitial(DataHolder::new);

    private ScenarioContext() {
        // private constructor to prevent instantiation
    }

    public static void set(String key, Object value) {
        context.get().getDataMap().put(key, value);
    }

    public static <T> T get(Supplier<Object> supplier, Class<T> type, String errorMessage) {
        return type.cast(Optional.ofNullable(supplier.get())
                .orElseThrow(() -> new RuntimeException(errorMessage)));
    }

    public static <T> T get(Supplier<T> supplier, String errorMessage) {
        return Optional.ofNullable(supplier.get())
                .orElseThrow(() -> new RuntimeException(errorMessage));
    }

    public static String getString(String key) {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, key);
        return get(() -> context.get().getDataMap().get(key), String.class, errorMessage);
    }

    public static Object getObject(String key) {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, key);
        return get(() -> context.get().getDataMap().get(key), errorMessage);
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
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, "response");
        return get(() -> context.get().getRestResponse(), errorMessage);
    }

    public static void setCommonResponse(CommonResponse response) {
        context.get().setCommonResponse(response);
    }

    public static CommonResponse getCommonResponse() {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, "common response");
        return get(() -> context.get().getCommonResponse(), errorMessage);
    }

    public static void setUser(UserEntity user) {
        context.get().setUser(user);
    }

    public static UserEntity getUser() {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, "user");
        return get(() -> context.get().getUser(), errorMessage);
    }

    public static void setPost(PostEntity post) {
        context.get().setPost(post);
    }
    public static PostEntity getPost() {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, "post");
        return get(() -> context.get().getPost(), errorMessage);
    }

    public static void setDataRows(List<Map<String, String>> rows) {
        context.get().setRows(rows);
    }

    public static List<Map<String, String>> getDataRows() {
        var errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE_TMPLT, "data rows");
        return get(() -> context.get().getRows(), errorMessage);
    }
}
