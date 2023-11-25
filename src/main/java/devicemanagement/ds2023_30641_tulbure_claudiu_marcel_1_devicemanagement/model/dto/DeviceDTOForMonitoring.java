package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeviceDTOForMonitoring implements Serializable {

    @NonNull
    private String token;
    @NonNull
    private String operation;
    @NonNull
    private UUID uuid;
    @NonNull
    private Integer maximHourlyEnergy;
}
