package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForAdmin;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.MappingUserToDevicesDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.Device;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper.DeviceMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.DeviceRepository;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UserRepository userRepository;

    public List<DeviceDTOForUser> getAllDevicesByUserId(UUID id) {
        return deviceMapper.entityListToDTO(deviceRepository.findAllByUserId(id));
    }

    @Transactional
    public void insertDevice(DeviceDTOForUser deviceDTOForUser) {
        deviceRepository.save(deviceMapper.dtoForUserToEntity(deviceDTOForUser));
    }
    @Transactional
    public void deleteDevice(UUID id) {
        if(deviceRepository.existsById(id)){
            deviceRepository.deleteById(id);
        }else {
            throw new RuntimeException("Device not found");
        }
    }
    @Transactional
    public void updateDevice(DeviceDTOForUser deviceDTOForUser,UUID id) {
        if(!deviceRepository.existsById(id)){
            throw new RuntimeException("Device not found");
        }
        Device device = deviceMapper.dtoForUserToEntity(deviceDTOForUser);
        device.setId(id);
        deviceRepository.save(device);
    }
    @Transactional
    public void manageUserDevicesRelationship(MappingUserToDevicesDTO mappingUserToDevicesDTO) throws RuntimeException{

        Optional<User> user = userRepository.findById(mappingUserToDevicesDTO.getUserId());
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        if(!userRepository.existsById(mappingUserToDevicesDTO.getUserId())){
            throw new RuntimeException("User not found");
        }
        List<Device> deviceList = deviceRepository.findAllByUserId(mappingUserToDevicesDTO.getUserId());
        List<Device> devicesToBeAdded = deviceRepository.findAllById(mappingUserToDevicesDTO.getDevicesList());

        deviceList.forEach(device -> device.setUser(null));
        devicesToBeAdded.forEach(device -> device.setUser(user.get()));

    }

    public List<DeviceDTOForAdmin> getAllDevices() {
        return deviceMapper.entityListToDTOForAdmin(deviceRepository.findAll());
    }
}
