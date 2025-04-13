package com.dmytrohont.test.utils.assertions;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dmytrohont.test.models.response.CommonResponse;

import java.util.List;

public class ResponseChecker {

    private ResponseChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static void checkSuccessResponse(CommonResponse response) {
        assertNotNull(response);
        assertTrue(response.isSuccess(), "The value for success should be true");
        assertNull(response.getMessage(), "The message should be empty");
        assertTrue(List.of(response.getValidationErrors()).isEmpty(), "The validation errors list should be empty");
    }
}
