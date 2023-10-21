package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.mapper;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto.UserDTO;
import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .build();
    }

}
