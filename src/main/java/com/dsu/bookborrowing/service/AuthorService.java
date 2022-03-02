package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.BookBorrowingApplication;
import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.repository.AuthorRepository;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Setter
public class AuthorService {


    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;


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
        for (Author auth : arr)
            arrDtro.add(convertToDTO(auth));
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
        return author.getAuthor_id() == null || !authorRepository.existsById(author.getAuthor_id())
                ? null : authorRepository.save(author);
    }


    public AuthorDTO convertToDTO(Author au) {
        if (au == null)
            return null;
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(au, AuthorDTO.class);
    }
}
