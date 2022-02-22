package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.RolModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends CrudRepository<RolModel, Integer> {
    Optional<RolModel> findById(Integer id);
}
