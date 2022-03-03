package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Author_book;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Author_bookRepository extends CrudRepository<Author_book, Integer> {


    ArrayList<Author_book> findBooksByAuthor(@Param("author") Author auth);


    ArrayList<Author_book> findAuthorsByBook(@Param("book") Book auth);


}
