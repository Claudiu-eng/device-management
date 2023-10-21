package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID>{

    List<Device> findAllByUserId(UUID id);

}
