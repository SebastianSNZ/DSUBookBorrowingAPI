package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findById(Integer id);
}
