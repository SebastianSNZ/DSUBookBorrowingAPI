package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public ArrayList<Book> getBooks(){
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public Book setBook(Book book){
        return bookRepository.save(book);
    }


}
