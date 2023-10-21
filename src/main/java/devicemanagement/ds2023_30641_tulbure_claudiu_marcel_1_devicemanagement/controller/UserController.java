package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.controller;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.MappingUserToDevicesDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.UserDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service.DeviceService;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("")
    public ResponseEntity insertUser(@RequestBody UserDTO userDTO){
        userService.insertUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all-devices-by-user-id/{id}")
    public ResponseEntity<List<DeviceDTOForUser>> getAllDevicesByUserId(@PathVariable UUID id){
        return ResponseEntity.ok(deviceService.getAllDevicesByUserId(id));
    }


}
