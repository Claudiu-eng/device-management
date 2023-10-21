package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.UserDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper.UserMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertUser(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
    }

    @Transactional
    public void deleteUser(UUID id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new RuntimeException("User not found");
        }
    }


}
