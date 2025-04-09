package config;

import static io.restassured.RestAssured.config;
import static utils.PropertyUtil.getProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RestConfig {

    private static final int DEFAULT_TIMEOUT = 3000;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);

    private RestConfig() {
    }

    public static RequestSpecification getRequestSpec() {

        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .setBaseUri(getProperty("baseUri"))
                .setContentType("application/json");

        int timeout;
        try {
            timeout = Integer.parseInt(getProperty("timeout", ""));
        } catch (NumberFormatException e) {
            LOGGER.warn("Invalid timeout value. Using default: {}", DEFAULT_TIMEOUT);
            timeout = DEFAULT_TIMEOUT;
        }

        // Create a new config with a custom timeout
        var config = new HttpClientConfig();
        config.setParam("http.connection.timeout", timeout)
                .setParam("http.socket.timeout", timeout)
                .setParam("http.connection-manager.timeout", (long) timeout);
        // The timeout is used for all the following connections:
        // - Connection timeout: time to establish a new connection
        // - Socket timeout: time to receive a response
        // - Connection manager timeout: time to wait for a free connection in the connection pool
        specBuilder.setConfig(config().httpClient(config));

        if (Boolean.parseBoolean(getProperty("logEnabled", "false"))) {
            specBuilder.log(LogDetail.ALL);
        }

        return specBuilder.build();
    }
}

