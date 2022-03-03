package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Author_book;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.service.Author_bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/authorBook")
public class Author_bookController {
    @Autowired
    Author_bookService author_bookService;

    @CrossOrigin
    @GetMapping
    public ArrayList<Author_book> getAuthor_books() {
        return author_bookService.getAuthors();
    }

    @CrossOrigin
    @PostMapping
    public Author_book setAuthor(@RequestBody Author_book author_book) {
        return author_bookService.setAuthorBook(author_book);
    }

    @CrossOrigin
    @GetMapping("/AuthorsByBook")
    ArrayList<AuthorDTO> getByBook(@RequestParam("book") Integer id) {

//        ArrayList<Author_book> arrAuth_book = author_bookService.getAuthorsByBook(new Book(id, "", "", 0));
//
//        List<Author> all =arrAuth_book.stream()
//                .map(o -> Arrays.asList(o.getAuthor())).flatMap(Collection::stream)
//                .collect(Collectors.toList());

        return author_bookService.getAuthorsByBook(new Book(id, "", "", 0));
    }

    @CrossOrigin
    @GetMapping("/booksByAuthor")
    ArrayList<BookDTO> getByAuthor(@RequestParam("author") Integer id) {
        return author_bookService.getBooksByAuthor(new Author(id, ""));
    }

}
