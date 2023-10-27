package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.DeviceNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForAdmin;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.MappingUserToDevicesDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.Device;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper.DeviceMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.DeviceRepository;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final UserRepository userRepository;

    public List<DeviceDTOForUser> getAllDevicesByUserId(UUID id) {
        return deviceMapper.entityListToDTO(deviceRepository.findAllByUserId(id));
    }

    @Transactional
    public void insertDevice(DeviceDTOForUser deviceDTOForUser) {
        Device device = deviceMapper.dtoForUserToEntity(deviceDTOForUser);
        device.setUser(userRepository.findById(deviceDTOForUser.getUserId()).get());
        deviceRepository.save(device);
    }
    @Transactional
    public void deleteDevice(UUID id) throws DeviceNotFoundException {
        if(deviceRepository.existsById(id)){
            deviceRepository.deleteById(id);
        }else {
            throw new DeviceNotFoundException();
        }
    }
    @Transactional
    public void updateDevice(DeviceDTOForUser deviceDTOForUser,UUID id) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(id).orElseThrow(DeviceNotFoundException::new);
        if(device.getUser()!=null){
            if(!device.getUser().getId().equals(deviceDTOForUser.getUserId())){
                User user = device.getUser();
                user.getDeviceList().remove(device);
                userRepository.save(user);
                device.setUser(userRepository.findById(deviceDTOForUser.getUserId()).get());
            }
        }
        device.setAddress(deviceDTOForUser.getAddress());
        device.setMaximHourlyEnergy(deviceDTOForUser.getMaximHourlyEnergy());
        device.setDescription(deviceDTOForUser.getDescription());
        deviceRepository.save(device);
    }


    public List<DeviceDTOForAdmin> getAllDevices() {
        return deviceMapper.entityListToDTOForAdmin(deviceRepository.findAll());
    }
}
