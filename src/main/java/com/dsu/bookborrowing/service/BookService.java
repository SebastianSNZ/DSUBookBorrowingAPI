package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public Book setBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getById(Integer id) {
        return bookRepository.findById(id);
    }


    public boolean deleteBook(Integer id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


    public Book updateBook(Book book) {
        if(book.getBook_id() == null)
            return null;
        if (bookRepository.existsById(book.getBook_id())) {
            return bookRepository.save(book);
        }
        return null;
    }

}
