package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.controller;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForAdmin;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.MappingUserToDevicesDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all-devices")
    public ResponseEntity<List<DeviceDTOForAdmin>> getAllDevices(){
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @PostMapping("/mapping-user-to-devices")
    public ResponseEntity manageUserDevicesRelationship(@RequestBody MappingUserToDevicesDTO mappingUserToDevicesDTO){
        try {
            deviceService.manageUserDevicesRelationship(mappingUserToDevicesDTO);
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity insertDevice(@RequestBody DeviceDTOForUser deviceDTOForUser){
        deviceService.insertDevice(deviceDTOForUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDevice(@PathVariable UUID id){
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDevice(@RequestBody DeviceDTOForUser deviceDTOForUser,@PathVariable UUID id){
        deviceService.updateDevice(deviceDTOForUser,id);
        return ResponseEntity.ok().build();
    }




}
