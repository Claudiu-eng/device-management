package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.DeviceNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForAdmin;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForMonitoring;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.DeviceDTOForUser;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.Device;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper.DeviceMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.DeviceRepository;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    @Value("${exchange.name}")
    private String exchangeName;
    @Value("${routing.key}")
    private String routingKey;
    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;


    public List<DeviceDTOForUser> getAllDevicesByUserId(UUID id) {
        return deviceMapper.entityListToDTO(deviceRepository.findAllByUserId(id));
    }

    @Transactional
    public void insertDevice(DeviceDTOForUser deviceDTOForUser) throws UserNotFoundException, JsonProcessingException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("JWT is missing");
        }
        jwt = authHeader.substring(7);

        Device device = deviceMapper.dtoForUserToEntity(deviceDTOForUser);
        User user = userRepository.findById(deviceDTOForUser.getUserId()).orElseThrow(UserNotFoundException::new);
        device.setUser(user);
        Device device1 = deviceRepository.save(device);
        DeviceDTOForMonitoring dto = DeviceDTOForMonitoring.builder().maximHourlyEnergy(device1.getMaximHourlyEnergy())
                .token(jwt).operation("INSERT").uuid(device1.getId()).build();
        String jsonString = objectMapper.writeValueAsString(dto);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonString);
    }

    @Transactional
    public void deleteDevice(UUID id) throws DeviceNotFoundException, JsonProcessingException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("JWT is missing");
        }
        jwt = authHeader.substring(7);
        Device device1 = deviceRepository.findById(id).orElseThrow(DeviceNotFoundException::new);
        DeviceDTOForMonitoring dto = DeviceDTOForMonitoring.builder().maximHourlyEnergy(device1.getMaximHourlyEnergy())
                .token(jwt).operation("DELETE").uuid(device1.getId()).build();
        String jsonString = objectMapper.writeValueAsString(dto);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonString);
        deviceRepository.deleteById(id);
    }

    @Transactional
    public void updateDevice(DeviceDTOForUser deviceDTOForUser, UUID id) throws DeviceNotFoundException, UserNotFoundException {
        Device device = deviceRepository.findById(id).orElseThrow(DeviceNotFoundException::new);
        if (device.getUser() != null) {
            if (!device.getUser().getId().equals(deviceDTOForUser.getUserId())) {
                User user = device.getUser();
                user.getDeviceList().remove(device);
                userRepository.save(user);
                User user1 = userRepository.findById(deviceDTOForUser.getUserId()).orElseThrow(UserNotFoundException::new);
                device.setUser(user1);
            }
        } else {
            User user1 = userRepository.findById(deviceDTOForUser.getUserId()).orElseThrow(UserNotFoundException::new);
            device.setUser(user1);
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
