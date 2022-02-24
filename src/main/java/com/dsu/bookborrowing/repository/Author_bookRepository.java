package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Author_book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Author_bookRepository extends CrudRepository<Author_book, Integer> {

}
