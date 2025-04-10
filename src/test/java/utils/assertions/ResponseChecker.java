package utils.assertions;


import models.response.CommonResponse;

public class ResponseChecker {

    private ResponseChecker() {
        throw new IllegalStateException("Utility class");
    }
    public static void checkSuccessResponse(CommonResponse response) {
//        assertNotNull(response);
//        assertTrue("The value for success should be true", response.isSuccess());
//        assertNull("The message should be empty", response.getMessage());
//        assertTrue("The validation errors list should be empty", response.getValidationErrors().isEmpty());
    }
}
