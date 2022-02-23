package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/Author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ArrayList<Author> getBooks(){
        return authorService.getAuthors();
    }

    @PostMapping
    public Author setBook(@RequestBody Author author){
        return authorService.setAuthor(author);
    }

}
