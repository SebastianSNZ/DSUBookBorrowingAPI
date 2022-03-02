package com.dsu.bookborrowing.controller;


import com.dsu.bookborrowing.BookBorrowingApplication;
import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.service.BookService;
import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/v1/book")

@Slf4j
@Api(value = "API Rest BOOK")
@CrossOrigin(origins = "*")
@Setter
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ArrayList<Book> getBooks() {
        log.info("entered to getBooks");

        return bookService.getBooks();
    }

    @PostMapping
    public BookDTO setBook(@RequestBody BookDTO book) {
        log.info("Creating a new book");
        return converToDTO( bookService.setBook( new Book(book)));
    }

    @GetMapping(path = "/{id}")
    public Optional<Book> getById(@PathVariable("id") Integer id) {
        log.info("Searching a book by id");
        return bookService.getById(id);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        log.info("Trying to delete the book " + id);
        boolean ok = bookService.deleteBook(id);
        if (ok) {
           log.info("Book with id " + id + " was removed");
            return "Book with id  " + id + " was removed";
        } else {
            log.warn("Book with id " + id + " could not removed.");
            return "Book with id " + id + " could not removed";
        }
    }

    @GetMapping(path = "/bookDTO")
    public ArrayList<BookDTO> getBookDTO() {
        log.info("Getting books but without id");
        return bookService.getBooksDTO();
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        log.info("updating the book with id " + book.getBook_id()) ;
        return bookService.updateBook(book);
    }


    public BookDTO converToDTO(Book bk){
        ModelMapper modelMapper = new ModelMapper();
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(bk, BookDTO.class);
    }
}
