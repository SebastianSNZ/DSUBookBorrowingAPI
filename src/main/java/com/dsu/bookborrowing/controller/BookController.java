package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.BookBorrowingApplication;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private static final Logger logger = BookBorrowingApplication.logger;

    @Autowired
    BookService bookService;

    @GetMapping
    public ArrayList<Book> getBooks() {
        logger.info("entered to getBooks");

        return bookService.getBooks();
    }

    @PostMapping
    public Book setBook(@RequestBody BookDTO book) {
        logger.info("Creating a new book");
        return bookService.setBook(new Book(book));
    }

    @GetMapping(path = "/{id}")
    public Optional<Book> getById(@PathVariable("id") Integer id) {
        logger.info("Searching a book by id");
        return bookService.getById(id);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        logger.info("Trying to delete the book " + id);
        boolean ok = bookService.deleteBook(id);
        if (ok) {
            logger.info("Book with id " + id + " was removed");
            return "Book with id  " + id + " was removed";
        } else {
            logger.warn("Book with id " + id + " could not removed.");
            return "Book with id " + id + " could not removed";
        }
    }

    @GetMapping(path = "/bookDTO")
    public ArrayList<BookDTO> getBookDTO() {
        logger.info("Getting books but without id");
        return bookService.getBooksDTO();
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        logger.info("updating the book with id " + book.getBook_id()) ;
        return bookService.updateBook(book);
    }


}
