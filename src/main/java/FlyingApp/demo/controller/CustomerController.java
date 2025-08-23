package FlyingApp.demo.controller;

import FlyingApp.demo.model.Customer;
import FlyingApp.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId){
         Optional<Customer> customer = customerService.getCustomerById(customerId);
         return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/{customerName}")
    public ResponseEntity<List<Customer>> getCustomerName(@PathVariable String customerName) {
        List<Customer> customers = customerService.findByCustomerName(customerName);
        return ResponseEntity.ok(customers);
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> putCustomerById(@PathVariable Long customerId, @RequestBody Customer customer) {
        Optional<Customer> updateCustomer = customerService.updateCustomer(customerId, customer);
        return updateCustomer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        if(customerService.deleteCustomer(customerId)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
