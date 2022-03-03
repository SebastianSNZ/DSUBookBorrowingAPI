package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Author_book;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.repository.AuthorRepository;
import com.dsu.bookborrowing.repository.Author_bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Author_bookService {
    @Autowired
    Author_bookRepository author_bookRepository;

    public ArrayList<Author_book> getAuthors() {
        return (ArrayList<Author_book>) author_bookRepository.findAll();
    }

    public Author_book setAuthorBook(Author_book author_book) {
        return author_bookRepository.save(author_book);
    }


    public ArrayList<AuthorDTO> getAuthorsByBook(Book bk) {
        ArrayList<Author_book> arrAB = author_bookRepository.findAuthorsByBook(bk);
        ArrayList<AuthorDTO> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add(new AuthorDTO(author_book.getAuthor()));
        return arrResp;
    }

    public ArrayList<BookDTO> getBooksByAuthor(Author auth) {
        ArrayList<Author_book> arrAB = author_bookRepository.findBooksByAuthor(auth);
        ArrayList<BookDTO> arrResp = new ArrayList<>(arrAB.size());
        for (Author_book author_book : arrAB)
            arrResp.add(new BookDTO(author_book.getBook()));
        return arrResp;
    }

}
