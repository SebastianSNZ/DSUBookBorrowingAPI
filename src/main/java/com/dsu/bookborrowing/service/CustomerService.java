package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public ArrayList<Customer> getCustomer(){
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer setCustomer(Customer customer){
        return customerRepository.save(customer);
    }



}
