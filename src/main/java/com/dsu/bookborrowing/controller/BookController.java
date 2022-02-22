package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/Book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ArrayList<Book>  getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public Book setBook(@RequestBody Book book){
        return bookService.setBook(book);
    }

}
