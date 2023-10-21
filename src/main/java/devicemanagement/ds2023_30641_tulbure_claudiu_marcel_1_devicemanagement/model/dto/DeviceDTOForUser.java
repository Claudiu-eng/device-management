package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTOForUser {

    @NonNull
    private String description;

    @NonNull
    private String address;

    @NonNull
    private Integer maximHourlyEnergy;

}
