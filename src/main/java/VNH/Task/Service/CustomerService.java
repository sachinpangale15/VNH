package VNH.Task.Service;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import VNH.Task.Entity.Customer;
import VNH.Task.Repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
@Validated
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) throws Exception {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }
    
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setName(customerDetails.getName());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    private void validateCustomer(Customer customer) throws Exception {
        // Sex should be "M" or "F"
        if (!"M".equalsIgnoreCase(customer.getSex()) && !"F".equalsIgnoreCase(customer.getSex())) {
            throw new IllegalArgumentException("Invalid sex value. It should be 'M' or 'F'");
        }

        // DOB should be before 01-01-2002
        if (customer.getDob().after(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"))) {
            throw new IllegalArgumentException("DOB should be before 01-01-2002");
        }

        // Contract Type should be fulltime or parttime
        if (!"fulltime".equalsIgnoreCase(customer.getContractType()) && !"parttime".equalsIgnoreCase(customer.getContractType())) {
            throw new IllegalArgumentException("Invalid contract type. It should be 'fulltime' or 'parttime'");
        }
    }
}
