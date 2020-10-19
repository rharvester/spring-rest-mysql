package org.jftm.service;

import org.jftm.entity.Customer;
import java.util.List;

public interface CustomerService {

        List<Customer> findAll();

        Customer findById(int theId);

        void save(Customer theEmployee);

        void deleteById(int theId);

}
