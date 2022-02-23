package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.BookBorrowingApplication;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/Book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookBorrowingApplication.class);

    @Autowired
    BookService bookService;

    @GetMapping
    public ArrayList<Book>  getBooks(){
        logger.info("entered to  getBooks");

        return bookService.getBooks();
    }

    @PostMapping
    public Book setBook(@RequestBody Book book){
        return bookService.setBook(book);
    }

}
