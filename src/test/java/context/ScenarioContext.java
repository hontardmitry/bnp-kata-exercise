package context;

import models.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import io.restassured.response.Response;

public class ScenarioContext {

    private static final String RESPONSE_KEY = "response";
    private static final String USER_KEY = "user";

    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    private ScenarioContext() {
    }

    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    public static <T> Optional<T> get(String key) {
        return Optional.ofNullable((T) context.get().get(key));
    }

    public static void clear() {
        context.get().clear();
    }

    public static void setResponse(Response response) {
        ScenarioContext.set(RESPONSE_KEY, response);
    }

    public static Response getResponse() {
        return ScenarioContext.<Response>get(RESPONSE_KEY)
                .orElseThrow(() -> new NoSuchElementException("No response found for the 'response' key"));
    }

    public static void setUser(UserEntity user) {
        ScenarioContext.set(USER_KEY, user);
    }

    public static UserEntity getUser() {
        return ScenarioContext.<UserEntity>get(USER_KEY)
                .orElseThrow(() -> new NoSuchElementException("No user found for the 'user' key"));
    }
}
