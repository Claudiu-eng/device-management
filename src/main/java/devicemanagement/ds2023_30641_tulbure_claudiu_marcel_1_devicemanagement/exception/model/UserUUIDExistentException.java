package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class UserUUIDExistentException extends CustomException{
    private static final String MESSAGE = "This user UUID already exists!";
    private static final HttpStatus httpStatus = HttpStatus.CONFLICT;
    public UserUUIDExistentException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
