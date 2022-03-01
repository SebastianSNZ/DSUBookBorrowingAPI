package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.*;
import com.dsu.bookborrowing.service.AuthorService;
import com.dsu.bookborrowing.service.Author_bookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/authorBook")

@Api(value = "API Rest AuthorBook")
@CrossOrigin(origins = "*")
public class Author_bookController {
    @Autowired
    Author_bookService author_bookService;

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ArrayList<Author_book> getAuthor_books() {
        return author_bookService.getAuthors();
    }

    @PostMapping
    public Author_book setAuthor(@RequestBody Author_book author_book) {
        return author_bookService.setAuthorBook(author_book);
    }

    @GetMapping("/AuthorsByBookWithID/{id}")
    public ArrayList<Author> getByBookWithId(@PathVariable("id") Integer id) {
        Book bk = new Book();
        bk.setBook_id(id);
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(bk);
        ArrayList<Author> arrResp = new ArrayList<>();
        for (Author_book author_book : arrAB)
            arrResp.add((author_book.getAuthor()));
        return arrResp;

    }

    @PostMapping("/delete")
    public boolean deleteAuthorBook(@RequestBody Author_book_keys key){
        return author_bookService.deleteAuthorBook(key);
    }



    @GetMapping("/AuthorsWhoAreNotIn/{id}")
    public ArrayList<Author> getAuthorsNByBoook(@PathVariable("id") Integer id) {
        ArrayList<Author> arrTodos = authorService.getAuthors();
        Book bk = new Book();
        bk.setBook_id(id);
        ArrayList<Author> arrEstan = new ArrayList<>();
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(bk);
        for (Author_book author_book : arrAB)
            arrEstan.add(author_book.getAuthor());

        arrTodos.removeAll(arrEstan);
        return arrTodos;
    }


    @GetMapping("/AuthorsByBook/{id}")
    public ArrayList<AuthorDTO> getByBook(@PathVariable("id") Integer id) {
        Book bk = new Book();
        bk.setBook_id(id);
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(bk);
        ArrayList<AuthorDTO> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add(convertAuthorToDTO(author_book.getAuthor()));
        return arrResp;
    }


    @GetMapping("/booksByAuthorWithID/{id}")
    public ArrayList<Book> getByAuthorWithId(@PathVariable("id") Integer id) {
        Author au = new Author();
        au.setAuthor_id(id);
        ArrayList<Author_book> arrAB = author_bookService.getBooksByAuthor(au);
        ArrayList<Book> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add((author_book.getBook()));
        return arrResp;
    }


    @GetMapping("/booksByAuthor/{id}")
    ArrayList<BookDTO> getByAuthor(@PathVariable("id") Integer id) {
        Author au = new Author();
        au.setAuthor_id(id);
        ArrayList<Author_book> arrAB = author_bookService.getBooksByAuthor(au);
        ArrayList<BookDTO> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add(converBookToDTO(author_book.getBook()));
        return arrResp;
    }


    @Autowired
    private ModelMapper modelMapper;


    public BookDTO converBookToDTO(Book bk) {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(bk, BookDTO.class);
    }

    public AuthorDTO convertAuthorToDTO(Author au) {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(au, AuthorDTO.class);
    }


}


