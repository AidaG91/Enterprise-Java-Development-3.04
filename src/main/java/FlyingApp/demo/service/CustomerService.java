package FlyingApp.demo.service;

import FlyingApp.demo.model.Customer;
import FlyingApp.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> updateCustomer(Long customerId, Customer customer){
        if (customerRepository.existsById(customerId)) {
            customer.setCustomerId(customerId);
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty();
    }

    public List<Customer> findByCustomerName(String customerName){
        return customerRepository.findByCustomerName(customerName);
    }

    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

}
