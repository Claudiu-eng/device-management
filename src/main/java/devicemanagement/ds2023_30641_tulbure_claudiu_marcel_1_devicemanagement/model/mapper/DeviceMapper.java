package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForAdmin;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceMapper {

    public DeviceDTOForUser entityToDTO(Device device){
        return DeviceDTOForUser.builder()
                .address(device.getAddress())
                .description(device.getDescription())
                .maximHourlyEnergy(device.getMaximHourlyEnergy())
                .userId(device.getUser().getId())
                .id(device.getId())
                .build();
    }

    public List<DeviceDTOForUser> entityListToDTO(List<Device> devices){
        return devices.stream().map(this::entityToDTO).toList();
    }

    public DeviceDTOForAdmin entityToDTOForAdmin(Device device){
        return DeviceDTOForAdmin.builder()
                .id(device.getId())
                .address(device.getAddress())
                .description(device.getDescription())
                .maximHourlyEnergy(device.getMaximHourlyEnergy())
                .userId((device.getUser() == null) ? null : device.getUser().getId())
                .build();
    }

    public List<DeviceDTOForAdmin> entityListToDTOForAdmin(List<Device> devices){
        return devices.stream().map(this::entityToDTOForAdmin).toList();
    }
    public Device dtoForUserToEntity(DeviceDTOForUser deviceDTO) {
        return Device.builder()
                .address(deviceDTO.getAddress())
                .description(deviceDTO.getDescription())
                .maximHourlyEnergy(deviceDTO.getMaximHourlyEnergy())
                .build();
    }


}
