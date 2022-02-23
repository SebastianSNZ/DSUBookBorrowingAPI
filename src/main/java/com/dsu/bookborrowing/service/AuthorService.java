package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ArrayList<Author>  getAuthors(){
        return (ArrayList<Author>) authorRepository.findAll();
    }

    public Author setAuthor(Author author){
        return authorRepository.save(author);
    }
}
