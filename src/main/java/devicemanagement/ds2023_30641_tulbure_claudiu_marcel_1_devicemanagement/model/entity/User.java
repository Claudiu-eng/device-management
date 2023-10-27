package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_device")
public class User {

    @Id
    private UUID id;

    @OneToMany(mappedBy="user")
    private List<Device> deviceList;

    @PreRemove
    private void removeUserFromDevices() {
        for (Device device : deviceList) {
            device.setUser(null);
        }
    }

}
