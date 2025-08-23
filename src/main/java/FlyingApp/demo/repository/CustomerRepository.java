package FlyingApp.demo.repository;

import FlyingApp.demo.enums.CustomerStatus;
import FlyingApp.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByCustomerName(String customerName);

    List<Customer> findByCustomerStatus(CustomerStatus customerStatus);

    @Query("SELECT a FROM Customer a WHERE a.customerName LIKE %:customerName%")
    List<Customer> findByNameContaining(@Param("customerName") String customerName);
}
