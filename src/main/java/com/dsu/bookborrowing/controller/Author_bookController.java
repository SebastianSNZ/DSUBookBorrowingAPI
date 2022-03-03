package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Author_book;
import com.dsu.bookborrowing.entity.Author_book_keys;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.service.AuthorService;
import com.dsu.bookborrowing.service.Author_bookService;
import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/authorBook")
@Slf4j
@Api(value = "API Rest AuthorBook")
@Setter
public class Author_bookController {
    @Autowired
    Author_bookService author_bookService;

    @Autowired
    AuthorService authorService;

    @CrossOrigin
    @GetMapping
    public ArrayList<Author_book> getAuthor_books() {

        log.info("Getting all authors_books");
        return author_bookService.getAuthorsBook();
    }

    @CrossOrigin
    @PostMapping
    public Author_book setAuthorBook(@RequestBody Author_book author_book) {
        log.info("Saving a new author_book");
        return author_bookService.setAuthorBook(author_book);
    }

    @CrossOrigin
    @GetMapping("/AuthorsByBookWithID/{id}")
    public ArrayList<Author> getAuthorsByBookWithId(@PathVariable("id") Integer id) {
        log.info("Getting the authors who work in the book with id " + id);
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(id);
        ArrayList<Author> arrResp = new ArrayList<>();
        for (Author_book author_book : arrAB)
            arrResp.add((author_book.getAuthor()));
        return arrResp;

    }

    @CrossOrigin
    @PostMapping("/delete")
    public boolean deleteAuthorBook(@RequestBody Author_book_keys key) {
        log.info("Deleting an author_book");
        return author_bookService.deleteAuthorBook(key);
    }

    @CrossOrigin
    @GetMapping("/AuthorsWhoAreNotIn/{id}")
    public ArrayList<Author> getAuthorsDontWorkInABook(@PathVariable("id") Integer id) {
        log.info("Getting the authors who don't work in the book with id " + id);
        ArrayList<Author> arrTodos = authorService.getAuthors();
        ArrayList<Author> arrEstan = new ArrayList<>();
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(id);
        for (Author_book author_book : arrAB)
            arrEstan.add(author_book.getAuthor());

        arrTodos.removeAll(arrEstan);
        return arrTodos;
    }

    @CrossOrigin
    @GetMapping("/AuthorsByBook/{id}")
    public ArrayList<AuthorDTO> getAuthorsByBook(@PathVariable("id") Integer id) {
        log.info("Getting the authorsDTO (authors without the ids) who work in the book with id " + id);
        ArrayList<Author_book> arrAB = author_bookService.getAuthorsByBook(id);
        ArrayList<AuthorDTO> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add(convertAuthorToDTO(author_book.getAuthor()));
        return arrResp;
    }

    @CrossOrigin
    @GetMapping("/booksByAuthorWithID/{id}")
    public ArrayList<Book> getBooksByAuthorWithId(@PathVariable("id") Integer id) {
        log.info("Getting books written by the author with id " + id);
        ArrayList<Author_book> arrAB = author_bookService.getBooksByAuthor(id);
        ArrayList<Book> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add((author_book.getBook()));
        return arrResp;
    }

    @CrossOrigin
    @GetMapping("/booksByAuthor/{id}")
    public ArrayList<BookDTO> getBooksByAuthor(@PathVariable("id") Integer id) {
        log.info("Getting the booksDTO (books without id) written by the author with id " + id);
        ArrayList<Author_book> arrAB = author_bookService.getBooksByAuthor(id);
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
