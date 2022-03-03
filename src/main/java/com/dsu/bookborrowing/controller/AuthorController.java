package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.service.AuthorService;
import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/v1/author")
@Slf4j
@Api(value = "API Rest Author")
@CrossOrigin(origins = "*")
@Setter
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;


    @CrossOrigin
    @GetMapping
    public ArrayList<Author> getAuthor() {
        log.info("Getting the authors list");
        return authorService.getAuthors();
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public Optional<Author> getById(@PathVariable("id") Integer id) {
        log.info("Searching the author with id " + id);

        return authorService.getById(id);
    }


    @CrossOrigin
    @GetMapping(path = "/onlyNames")
    public ArrayList<AuthorDTO> getAuthorNames() {

        log.info("Getting the AuthorDTO list (Authors without id) ");
        return authorService.getAuthorsNames();
    }

    @CrossOrigin
    @PostMapping
    public AuthorDTO setAuthor(@RequestBody AuthorDTO author) {
        log.info("Adding a new author");
        return convertToDTO(authorService.setAuthor(new Author(author)));
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        log.info("deleting the author  with id " + id);
        boolean ok = authorService.deleteAuthor(id);
        if (ok) {
            log.info("Author with id  " + id + " was removed.");
            return "Author with id  " + id + " was removed.";
        } else {
            log.info("Author with id  " + id + " could not removed.");
            return "Author with id  " + id + " could not removed.";
        }
    }

    @CrossOrigin
    @PutMapping
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }


    public AuthorDTO convertToDTO(Author au){
        if(au == null)
            return null;
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(au, AuthorDTO.class);
    }
}
