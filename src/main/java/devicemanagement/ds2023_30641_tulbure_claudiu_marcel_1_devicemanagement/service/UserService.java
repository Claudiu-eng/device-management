package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.service;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserNotFoundException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.exception.model.UserUUIDExistentException;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.UserDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper.UserMapper;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void insertUser(UserDTO userDTO) throws UserUUIDExistentException {
        if(userRepository.existsById(userDTO.getId())) {
            throw new UserUUIDExistentException();
        }
        userRepository.save(userMapper.toEntity(userDTO));
    }

    @Transactional
    public void deleteUser(UUID id) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new UserNotFoundException();
        }
    }


}
