package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NonNull
    private UUID id;

}
