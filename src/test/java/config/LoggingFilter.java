package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        LOGGER.debug("Request URI: {}", requestSpec.getURI());
        LOGGER.debug("Request method: {}", requestSpec.getMethod());
        LOGGER.debug("Request body: {}", (Object) requestSpec.getBody());

        Response response = ctx.next(requestSpec, responseSpec);

        LOGGER.debug("Response status: {}", response.getStatusCode());
        LOGGER.debug("Response body: {}", response.getBody().asPrettyString());

        return response;
    }
}
