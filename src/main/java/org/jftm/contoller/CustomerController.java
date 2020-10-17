package org.jftm.contoller;

import org.jftm.entity.Customer;
import org.jftm.exception.CustomerNotFoundException;
import org.jftm.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id {" + id + "} could not be found");
        }

        return customer.get();
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){

        return new ResponseEntity<>(customerRepository.save(customer),HttpStatus.CREATED);
    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){

        return new ResponseEntity<>(customerRepository.save(customer),HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id {" + id + "} could not be found");
        }

        customerRepository.delete(customer.get());

        return "Deleted customer id - " + id;
    }
}
