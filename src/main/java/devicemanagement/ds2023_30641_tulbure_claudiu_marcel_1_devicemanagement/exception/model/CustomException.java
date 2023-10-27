package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class CustomException extends Exception {
    private final HttpStatus status ;
    private final List<String> validationErrors;

    public CustomException(String message, HttpStatus status, List<String> errors) {
        super(message);
        this.validationErrors = errors;
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
