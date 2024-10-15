package VNH.Task.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import VNH.Task.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
