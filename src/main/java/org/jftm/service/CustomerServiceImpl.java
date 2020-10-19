package org.jftm.service;

import org.jftm.entity.Customer;
import org.jftm.exception.CustomerNotFoundException;
import org.jftm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> customer = repository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id {" + id + "} could not be found");
        }

        return customer.get();
    }

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        Optional<Customer> customer = repository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id {" + id + "} could not be found");
        }

        repository.delete(customer.get());
    }
}
