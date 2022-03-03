package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @CrossOrigin
    @GetMapping
    public ArrayList<Author> getAuthor() {
        return authorService.getAuthors();
    }

    @CrossOrigin
    @PostMapping
    public Author setAuthor(@RequestBody AuthorDTO author) {
        return authorService.setAuthor(new Author(author));
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public Optional<Author> getById(@PathVariable("id") Integer id) {
        return authorService.getById(id);
    }

    @CrossOrigin
    @GetMapping(path = "/onlyNames")
    public ArrayList<AuthorDTO> getAuthorNames() {
        return authorService.getAuthorsNames();
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = authorService.deleteAuthor(id);
        if (ok) {
            return "Author with id  " + id + " was removed.";
        } else {
            return "Author with id  " + id + " could not removed.";
        }
    }

    @CrossOrigin
    @PutMapping
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

}
