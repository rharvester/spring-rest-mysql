package org.jftm.contoller;

import org.jftm.entity.Customer;
import org.jftm.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        customerService.save(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/customers/" + customer.getId());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/customers")
    public ResponseEntity updateCustomer(@RequestBody Customer customer){

        customerService.save(customer);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {

        customerService.deleteById(id);

        return "Deleted customer id - " + id;
    }
}
