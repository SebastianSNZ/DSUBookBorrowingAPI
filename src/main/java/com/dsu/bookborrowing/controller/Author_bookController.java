package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.entity.Author_book;
import com.dsu.bookborrowing.service.Author_bookService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/Author_book")
public class Author_bookController {
    @Autowired
    Author_bookService author_bookService;


    @GetMapping
    public ArrayList<Author_book> getAuthor_books() {
        return author_bookService.getAuthors();
    }

    @PostMapping
    public Author_book setAuthor(@RequestBody Author_book author_book) {
        return author_bookService.setAuthor(author_book);
    }

}
