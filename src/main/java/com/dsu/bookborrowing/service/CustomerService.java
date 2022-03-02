package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public Customer setCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getById(Integer id){
        return  customerRepository.findById(id);
    }

    public ArrayList<Customer> getByRol(Integer rol){
        return customerRepository.findByRolId(rol);
    }

    public  boolean deleteCustomer(Integer id){
        try {
            customerRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
