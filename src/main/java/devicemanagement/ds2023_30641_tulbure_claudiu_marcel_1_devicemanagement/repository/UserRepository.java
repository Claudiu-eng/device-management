package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.repository;

import devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
