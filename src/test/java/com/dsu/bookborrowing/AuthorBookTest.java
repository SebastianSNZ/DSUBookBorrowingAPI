package com.dsu.bookborrowing;

import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.controller.Author_bookController;
import com.dsu.bookborrowing.entity.Author;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.repository.Author_bookRepository;
import com.dsu.bookborrowing.service.Author_bookService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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


@ActiveProfiles("test")
@SpringBootTest(classes = BookBorrowingApplication.class)
public class AuthorBookTest {
    @Autowired
    Author_bookService author_bookService;

    @Autowired
    Author_bookRepository author_bookRepository;

    @Autowired
    Author_bookController author_bookController;


    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    //region data
    Book book1 = new Book(1, "book1", "cat1", 12);
    Book book2 = new Book(2, "book2", "cat1", 12);
    Book book3 = new Book(3, "book3", "cat1", 12);


    Author auth1 = new Author(1, "name1", "last name1", "direction1", 12);
    Author auth2 = new Author(2, "name2", "last name2", "direction2", 22);
    Author auth3 = new Author(3, "name3", "last name3", "direction3", 32);
    //endregion


    //region controller

    @Test
    void getAuthor_booksController() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book1, auth2);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2));
        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.getAuthorsBook()).thenReturn(arrAuthor_books);

        ArrayList<Author_book> arrRes = author_bookController.getAuthor_books();

        MatcherAssert.assertThat(arrAuthor_books.get(0).getAuthor().getName(), Matchers.equalTo(arrRes.get(0).getAuthor().getName()));
    }

    @Test
    void setAuthorBookController() {
        Author_book au1 = new Author_book(book1, auth1);

        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.setAuthorBook(au1)).thenReturn(au1);
        Author_book res = this.author_bookController.setAuthorBook(au1);

        MatcherAssert.assertThat(au1.getAuthor().getName(), Matchers.equalTo(res.getAuthor().getName()));
    }


    @Test
    void getAuthorsByBookWithIdController() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book1, auth2);
        Author_book au3 = new Author_book(book1, auth3);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2, au3));

        Author_bookService author_bookService = getAuthor_bookService();
        Mockito.when(author_bookService.getAuthorsByBook(1)).thenReturn(arrAuthor_books);

        ArrayList<Author> result = author_bookController.getAuthorsByBookWithId(1);

        MatcherAssert.assertThat(arrAuthor_books.get(0).getAuthor().getName(),
                Matchers.equalTo(result.get(0).getName()));
    }

    @Test
    void deleteAuthorBookController() {

        Author_book_keys author_book_keys = new Author_book_keys(1, 1);

        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.deleteAuthorBook(author_book_keys)).thenReturn(true);

        boolean bl = author_bookController.deleteAuthorBook(author_book_keys);

        MatcherAssert.assertThat(bl,
                Matchers.equalTo(true));
    }

    @Test
    void getAuthorsDontWorkInABookController() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book1, auth2);


        ArrayList<Author> authorArrayList = new ArrayList<>(Arrays.asList(auth1, auth2, auth3));
        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2));
        Author_bookService author_bookService = getAuthor_bookService();
        AuthorService authorService = Mockito.mock(AuthorService.class);
        this.author_bookController.setAuthorService(authorService);
        Mockito.when(author_bookService.getAuthorsByBook(1)).thenReturn(arrAuthor_books);
        Mockito.when(authorService.getAuthors()).thenReturn(authorArrayList);
        ArrayList<Author> result = author_bookController.getAuthorsDontWorkInABook(1);

        MatcherAssert.assertThat(auth3.getName(),
                Matchers.equalTo(result.get(0).getName()));

    }

    @Test
    void getAuthorsByBookController() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book1, auth2);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2));
        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.getAuthorsByBook(1)).thenReturn(arrAuthor_books);

        ArrayList<AuthorDTO> result = author_bookController.getAuthorsByBook(1);

        MatcherAssert.assertThat(auth1.getName(),
                Matchers.equalTo(result.get(0).getName()));


    }

    @Test
    void getBooksByAuthorWithIdController() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book2, auth1);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2));
        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.getBooksByAuthor(1)).thenReturn(arrAuthor_books);

        ArrayList<Book> result = author_bookController.getBooksByAuthorWithId(1);

        MatcherAssert.assertThat(book1.getName(),
                Matchers.equalTo(result.get(0).getName()));

    }

    @Test
    void getBooksByAuthorController() {
        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book2, auth1);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2));
        Author_bookService author_bookService = getAuthor_bookService();

        Mockito.when(author_bookService.getBooksByAuthor(1)).thenReturn(arrAuthor_books);

        ArrayList<BookDTO> result = author_bookController.getBooksByAuthor(1);

        MatcherAssert.assertThat(book1.getName(),
                Matchers.equalTo(result.get(0).getName()));
    }


    //endregion


    //region service


    @Test
    void getAuthorsBookService() {

        Author_book au1 = new Author_book(book1, auth1);
        Author_book au2 = new Author_book(book1, auth2);
        Author_book au3 = new Author_book(book1, auth3);

        ArrayList<Author_book> arrAuthor_books = new ArrayList<>(Arrays.asList(au1, au2, au3));

        Author_bookRepository author_bookRepository = getAuthor_bookRepository();

        Mockito.when(author_bookRepository.findAll()).thenReturn(arrAuthor_books);

        ArrayList<Author_book> result = author_bookService.getAuthorsBook();


        MatcherAssert.assertThat(result.get(0).getAuthor().getName(),
                Matchers.equalTo(arrAuthor_books.get(0).getAuthor().getName()));
    }

    @Test
    void setAuthorBookService() {
        Author_book au1 = new Author_book(book1, auth1);

        Author_bookRepository author_bookRepository = getAuthor_bookRepository();

        Mockito.when(author_bookRepository.save(au1)).thenReturn(au1);

        Author_book result = author_bookService.setAuthorBook(au1);

        MatcherAssert.assertThat(au1.getAuthor().getName(),
                Matchers.equalTo(result.getAuthor().getName()));

    }

    //endregion

    @Test
    void getAndSaveAuthorBooksRepository() {
        author_bookRepository.deleteAll();
        authorRepository.deleteAll();;
        bookRepository.deleteAll();

        Author_book aub1 = new Author_book(book1, auth1);

        authorRepository.save(auth1);
        bookRepository.save(book1);
        Author_book aux = author_bookRepository.save(aub1);

        MatcherAssert.assertThat(aub1.getAuthor().getName(),
                Matchers.equalTo(aux.getAuthor().getName()));

        ArrayList<Author_book> arr = (ArrayList<Author_book>) author_bookRepository.findAll();

        MatcherAssert.assertThat(arr.size(), Matchers.equalTo(1));
    }

    //region repository

    //endregion


    ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper;
    }

    Author_bookRepository getAuthor_bookRepository() {
        Author_bookRepository authorRepository = Mockito.mock(Author_bookRepository.class);
        this.author_bookService.setAuthor_bookRepository(authorRepository);
        return authorRepository;
    }

    Author_bookService getAuthor_bookService() {
        Author_bookService authorService = Mockito.mock(Author_bookService.class);
        this.author_bookController.setAuthor_bookService(authorService);
        return authorService;
    }

}
