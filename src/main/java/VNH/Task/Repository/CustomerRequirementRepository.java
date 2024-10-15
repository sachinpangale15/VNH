package VNH.Task.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import VNH.Task.Entity.CustomerRequirement;

public interface CustomerRequirementRepository extends JpaRepository<CustomerRequirement, Long> {
}
