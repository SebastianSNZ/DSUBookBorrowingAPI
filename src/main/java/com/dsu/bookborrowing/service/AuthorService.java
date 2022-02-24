package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ArrayList<Author> getAuthors() {
        return (ArrayList<Author>) authorRepository.findAll();
    }

    public Author setAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getById(Integer id) {
        return authorRepository.findById(id);
    }


    public ArrayList<AuthorDTO> getAuthorsNames() {
        ArrayList<Author> arr = (ArrayList<Author>) authorRepository.findAll();



        ArrayList<AuthorDTO> arrDtro = new ArrayList<>(arr.size());


        for(Author auth : arr){
            arrDtro.add( new AuthorDTO(auth));
        }

        return arrDtro;
    }

    public boolean deleteAuthor(Integer id) {
        try {
            authorRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public Author updateAuthor(Author author) {
        if (author.getAuthor_id() == null || !authorRepository.existsById(author.getAuthor_id())) {
            return null;
        }
        return authorRepository.save(author);
    }

}
