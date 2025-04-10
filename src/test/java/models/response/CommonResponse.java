package models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private int status;
    private boolean success;
    private String message;
    private List<String> validationErrors;
    private String token;
    private int userId;
}
