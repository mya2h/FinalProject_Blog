package CS544.Helper;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus status;
    private Boolean success;
    private String message;

    public Response(HttpStatus httpStatus, boolean success, String message) {
        this.status = httpStatus;
        this.success= success;
        this.message = message;
    }
}
