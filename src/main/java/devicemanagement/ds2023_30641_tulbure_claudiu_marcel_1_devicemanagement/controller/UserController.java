package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.controller;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserUUIDExistentException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.UserDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("")
    public ResponseEntity insertUser(@RequestBody UserDTO userDTO) throws UserUUIDExistentException {
        userService.insertUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
