package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(Integer id);
    ArrayList<Customer> findByRolId(@Param("id") Integer id);

}
