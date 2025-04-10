package utils.assertions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import models.response.CommonResponse;

public class ResponseChecker {

    public static void checkSuccessResponse(CommonResponse response) {
        assertNotNull(response);
        assertTrue("The value for success should be true", response.isSuccess());
        assertNull("The message should be empty", response.getMessage());
        assertTrue("The validation errors list should be empty", response.getValidationErrors().isEmpty());
    }
}
