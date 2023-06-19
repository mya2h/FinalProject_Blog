package CS544.Dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response {
//    private HttpStatus status;
    private Boolean success;
    private String message;
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
