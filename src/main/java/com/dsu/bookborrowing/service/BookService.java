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

    public ArrayList<Book> getBooks(){
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public Book setBook(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
       return bookRepository.findById(id);
    }

    public boolean updateBookByAddingReservation(Long id) {
        Optional<Book> bookOptional = getBookById(id);
        if (bookOptional.isEmpty()) {
            return false;
        }
        Book book = bookOptional.get();
        if (book.getQuantity() < 1) {
            return false;
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        return true;
    }


}
