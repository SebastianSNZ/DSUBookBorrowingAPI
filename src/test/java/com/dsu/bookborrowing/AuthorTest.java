package com.dsu.bookborrowing;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.controller.AuthorController;
import com.dsu.bookborrowing.entity.*;
import com.dsu.bookborrowing.repository.*;
import com.dsu.bookborrowing.service.AuthorService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.isA;

@ActiveProfiles("test")
@SpringBootTest(classes = BookBorrowingApplication.class)
public class AuthorTest {

    @Autowired
    AuthorController authorController;

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    Author auth1 = new Author(1, "name1", "last name1", "direction1", 12);
    Author auth2 = new Author(2, "name2", "last name2", "direction2", 22);
    Author auth3 = new Author(3, "name3", "last name3", "direction3", 32);

//region Controller


    @Test
    void getAuthorController() {
        ArrayList<Author> authorList = new ArrayList<>(Arrays.asList(auth1, auth2, auth3));

        AuthorService authorService = getAuthorService();

        //When
        Mockito.when(authorService.getAuthors()).thenReturn(authorList);

        ArrayList<Author> result = authorController.getAuthor();
        //THEN
        MatcherAssert.assertThat(result.get(0).getName(), Matchers.equalTo(authorList.get(0).getName()));

    }

    @Test
    void getByIDController() {
        // with
        ModelMapper modelMapper = getMapper();
        Author auth4 = new Author(0, "name4", "last name4", "direction4", 42);

        AuthorDTO au = modelMapper.map(auth4, AuthorDTO.class);


        AuthorService authorService = getAuthorService();

        //When
        Optional<Author> op = Optional.of(auth4);
        Mockito.when(authorService.setAuthor(auth4)).thenReturn(auth4);
        Mockito.when(authorService.getById(0)).thenReturn(op);


        Optional<Author> opauResult = this.authorController.getById(0);
        Author auResult = opauResult.orElse(null);
        //Then
        MatcherAssert.assertThat(auResult, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void setAuthorController() {
        // with
        ModelMapper modelMapper = getMapper();
        Author auth4 = new Author(0, "name4", "last name4", "direction4", 42);

        AuthorDTO au = modelMapper.map(auth4, AuthorDTO.class);


        AuthorService authorService = getAuthorService();

        //When
        Mockito.when(authorService.setAuthor(auth4)).thenReturn(auth4);
        AuthorDTO auResult = this.authorController.setAuthor(au);

        //Then
        MatcherAssert.assertThat(auResult.getName(), Matchers.equalTo(auth4.getName()));
    }

    @Test
    public void deleteByIdController() {

        ModelMapper modelMapper = getMapper();

        Author auth4 = new Author(2, "name4", "last name4", "direction4", 42);
        AuthorDTO au = modelMapper.map(auth4, AuthorDTO.class);
        AuthorService authorService = getAuthorService();
        Boolean bool = false;
        Mockito.when(authorService.setAuthor(auth4)).thenReturn(auth4);
        Mockito.when(authorService.deleteAuthor(0)).thenReturn(bool);

        MatcherAssert.assertThat(bool, Matchers.equalTo(false));

    }

    @Test
    public void updateAuthorController() {

        ModelMapper modelMapper = getMapper();
        Author auth4 = new Author(0, "name4", "last name4", "direction4", 42);

        AuthorDTO au = modelMapper.map(auth4, AuthorDTO.class);


        AuthorService authorService = getAuthorService();

        //When
        Mockito.when(authorService.setAuthor(auth4)).thenReturn(auth4);
        AuthorDTO auResult = this.authorController.setAuthor(au);

        Author authRes = new Author();
        auth4.setName("juanes");
        Mockito.when(authorService.updateAuthor(auth4)).thenReturn(auth4);

        authRes = this.authorController.updateAuthor(auth4);

        MatcherAssert.assertThat(authRes.getName(), Matchers.equalTo(auth4.getName()));

    }

    @Test
    void getAuthorNamesController() {
        // with
        ModelMapper modelMapper = getMapper();
        List<Author> authorList = new ArrayList<>(Arrays.asList(auth1, auth2, auth3));

        AuthorService authorService = getAuthorService();

        ArrayList<AuthorDTO> authorDTOList = new ArrayList<>(authorList.stream().map(
                author -> modelMapper.map(author, AuthorDTO.class)
        ).collect(Collectors.toList()));


        //When
        Mockito.when(authorService.getAuthorsNames()).thenReturn(authorDTOList);

        ArrayList<AuthorDTO> result = authorController.getAuthorNames();
        //THEN
        MatcherAssert.assertThat(result.get(0).getName(), Matchers.equalTo(authorDTOList.get(0).getName()));

    }
    //endregion


//region Service

    @Test
    void getAuthorsService() {
        //With
        ArrayList<Author> authorList = new ArrayList<>(Arrays.asList(auth1, auth2, auth3));

        AuthorRepository authorRepository = getAuthorRepository();

        //When
        Mockito.when(authorRepository.findAll()).thenReturn(authorList);
        ArrayList<Author> authResult = this.authorService.getAuthors();

        //Then
        MatcherAssert.assertThat(authResult.get(0).getName(), Matchers.equalTo(authorList.get(0).getName()));
    }


    @Test
    void getAuthorsNameService() {
        ArrayList<Author> authorList = new ArrayList<>(Arrays.asList(auth1, auth2, auth3));

        AuthorRepository authorRepository = getAuthorRepository();

        //When
        Mockito.when(authorRepository.findAll()).thenReturn(authorList);
        ArrayList<AuthorDTO> authResult = this.authorService.getAuthorsNames();

        //Then
        MatcherAssert.assertThat(authResult.get(0).getName(), Matchers.equalTo(authorList.get(0).getName()));

    }

    @Test
    void setAuthorService() {
        Author auth4 = new Author(4, "name4", "last name4", "direction4", 42);

        AuthorRepository authorRepository = getAuthorRepository();

        Mockito.when(authorRepository.save(auth4)).thenReturn(auth4);

        Author authRes = this.authorService.setAuthor(auth4);

        MatcherAssert.assertThat(authRes.getName(), Matchers.equalTo(auth4.getName()));

    }

    @Test
    void getByidService() {
        Author auth4 = new Author(4, "name4", "last name4", "direction4", 42);
        AuthorRepository authorRepository = getAuthorRepository();
        Mockito.when(authorRepository.save(auth4)).thenReturn(auth4);

        Optional<Author> opAuth = Optional.of(auth4);
        Mockito.when((authorRepository.findById(4))).thenReturn(opAuth);

        Author authRes = this.authorService.setAuthor(auth4);

        MatcherAssert.assertThat(authRes, Matchers.not(Matchers.nullValue()));
    }

    @Test
    void deleteAuthorService() {
        Author auth4 = new Author(4, "name4", "last name4", "direction4", 42);
        AuthorRepository authorRepository = getAuthorRepository();
        Mockito.when(authorRepository.save(auth4)).thenReturn(auth4);


        Mockito.doNothing().when(authorRepository).deleteById(isA(Integer.class));
        authorRepository.deleteById(4);
    }

    @Test
    void updateAuthorService() {

        Author auth4 = new Author(0, "name4", "last name4", "direction4", 42);

        AuthorRepository authorRepository = getAuthorRepository();

        Mockito.when(authorRepository.save(auth4)).thenReturn(auth4);
        Author authRes = this.authorService.setAuthor(auth4);

        System.out.println(this.authorService.getAuthors());
        auth4.setName("juanes");
        Mockito.when(authorRepository.save(auth4)).thenReturn(auth4);
        Author authRes2 = this.authorService.updateAuthor(auth4);

        MatcherAssert.assertThat(authRes.getName(), Matchers.equalTo(auth4.getName()));


    }
//endregion


    //region repository

    @Test
    void getAuthorsRepository() {
        authorRepository.deleteAll();

        Author auth4 = new Author(4, "name4", "last name4", "direction4", 42);

        Author aux = authorRepository.save(auth4);

        ArrayList<Author> arr = (ArrayList<Author>) authorRepository.findAll();

        MatcherAssert.assertThat(arr.size(), Matchers.equalTo(1));
    }

    @Test
    void saveRepository() {
        authorRepository.deleteAll();
        Author auth4 = new Author(4, "name4", "last name4", "direction4", 42);
        Author aux = authorRepository.save(auth4);
        MatcherAssert.assertThat(aux.getName(), Matchers.equalTo(auth4.getName()));
    }


    //endregion


    ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper;
    }

    AuthorRepository getAuthorRepository() {
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        this.authorService.setAuthorRepository(authorRepository);
        return authorRepository;
    }

    AuthorService getAuthorService() {
        AuthorService authorService = Mockito.mock(AuthorService.class);
        this.authorController.setAuthorService(authorService);
        return authorService;
    }
}
