package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTOForAdmin {

    @NonNull
    private UUID id;
    @NonNull
    private String description;
    @NonNull
    private String address;
    @NonNull
    private Integer maximHourlyEnergy;
    private UUID userId;

}

