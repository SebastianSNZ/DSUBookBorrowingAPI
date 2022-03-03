package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.*;
import com.dsu.bookborrowing.repository.AuthorRepository;
import com.dsu.bookborrowing.repository.Author_bookRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Setter
public class Author_bookService {
    @Autowired
    Author_bookRepository author_bookRepository;

    public ArrayList<Author_book> getAuthorsBook() {
        return (ArrayList<Author_book>) author_bookRepository.findAll();
    }

    public Author_book setAuthorBook(Author_book author_book) {
        return author_bookRepository.save(author_book);
    }


    public ArrayList<Author_book> getAuthorsByBook(int id) {
        Book bk = new Book();
        bk.setBook_id(id);
       return author_bookRepository.findAuthorsByBook(bk);

    }

    public boolean deleteAuthorBook(Author_book_keys key) {
        try {
            author_bookRepository.deleteById(key);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


    public ArrayList<Author_book> getBooksByAuthor(Integer id) {
        Author au = new Author();
        au.setAuthor_id(id);
        return author_bookRepository.findBooksByAuthor(au);
    }

}
