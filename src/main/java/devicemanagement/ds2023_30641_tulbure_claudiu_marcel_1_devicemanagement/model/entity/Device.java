package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private String address;

    private Integer maximHourlyEnergy;

    @ManyToOne
    private User user;

}
