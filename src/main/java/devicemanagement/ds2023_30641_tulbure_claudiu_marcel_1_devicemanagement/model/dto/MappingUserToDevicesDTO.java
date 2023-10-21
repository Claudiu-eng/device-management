package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MappingUserToDevicesDTO {

    @NonNull
    private UUID userId;

    @NonNull
    private List<UUID> devicesList;

}
