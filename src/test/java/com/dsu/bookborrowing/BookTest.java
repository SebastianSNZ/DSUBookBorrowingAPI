package com.dsu.bookborrowing;

import com.dsu.bookborrowing.DTO.AuthorDTO;
import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.controller.AuthorController;
import com.dsu.bookborrowing.controller.BookController;
import com.dsu.bookborrowing.entity.*;
import com.dsu.bookborrowing.repository.*;
import com.dsu.bookborrowing.service.AuthorService;
import com.dsu.bookborrowing.service.BookService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.isA;

@ActiveProfiles("test")
@SpringBootTest(classes = BookBorrowingApplication.class)
public class BookTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BookController bookController;

    //region Controller
    Book book1 = new Book(1, "book1", "cat1", 12);
    Book book2 = new Book(2, "book2", "cat1", 12);
    Book book3 = new Book(3, "book3", "cat1", 12);

    @Test
    void getBooksController() {

        ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2, book3));

        BookService bookService = getBookService();

        Mockito.when((bookService.getBooks())).thenReturn(bookList);
        ArrayList<Book> result = bookController.getBooks();

        MatcherAssert.assertThat(result.get(0).getName(), Matchers.equalTo(bookList.get(0).getName()));


    }

    @Test
    void setBookController() {
        // with
        ModelMapper modelMapper = getMapper();
        Book book4 = new Book(0, "name4", "direction4", 42);

        BookDTO au = modelMapper.map(book4, BookDTO.class);


        BookService BookService = getBookService();

        //When
        Mockito.when(BookService.setBook(book4)).thenReturn(book4);
        BookDTO bookResult = this.bookController.setBook(au);

        //Then
        MatcherAssert.assertThat(bookResult.getName(), Matchers.equalTo(book4.getName()));

    }

    @Test
    void getByIdController() {
        ModelMapper modelMapper = getMapper();
        Book book4 = new Book(0, "name4", "last name4", 42);

        BookDTO au = modelMapper.map(book4, BookDTO.class);


        BookService BookService = getBookService();

        //When
        Optional<Book> op = Optional.of(book4);
        Mockito.when(BookService.setBook(book4)).thenReturn(book4);
        Mockito.when(BookService.getById(0)).thenReturn(op);


        Optional<Book> opauResult = this.bookController.getById(0);
        Book auResult = opauResult.orElse(null);
        //Then
        MatcherAssert.assertThat(auResult, Matchers.not(Matchers.nullValue()));


    }

    @Test
    void deleteByIdController() {
        ModelMapper modelMapper = getMapper();

        Book book4 = new Book(2, "name4", "direction4", 42);
        BookDTO au = modelMapper.map(book4, BookDTO.class);
        BookService BookService = getBookService();
        Boolean bool = false;
        Mockito.when(BookService.setBook(book4)).thenReturn(book4);
        Mockito.when(BookService.deleteBook(0)).thenReturn(bool);

        MatcherAssert.assertThat(bool, Matchers.equalTo(false));
    }

    @Test
    void getBookDTOController() {
        ModelMapper modelMapper = getMapper();
        List<Book> BookList = new ArrayList<>(Arrays.asList(book1, book2, book3));

        BookService BookService = getBookService();

        ArrayList<BookDTO> BookDTOList = new ArrayList<>(BookList.stream().map(
                Book -> modelMapper.map(Book, BookDTO.class)
        ).collect(Collectors.toList()));


        //When
        Mockito.when(BookService.getBooksDTO()).thenReturn(BookDTOList);

        ArrayList<BookDTO> result = this.bookController.getBookDTO();
        //THEN
        MatcherAssert.assertThat(result.get(0).getName(), Matchers.equalTo(BookDTOList.get(0).getName()));


    }

    @Test
    void updateBookController() {
        ModelMapper modelMapper = getMapper();
        Book book4 = new Book(0, "name4", "direction4", 42);

        BookDTO au = modelMapper.map(book4, BookDTO.class);


        BookService BookService = getBookService();

        //When
        Mockito.when(BookService.setBook(book4)).thenReturn(book4);
        BookDTO auResult = this.bookController.setBook(au);

        Book bookRes = new Book();
        book4.setName("juanes");
        Mockito.when(BookService.updateBook(book4)).thenReturn(book4);

        bookRes = this.bookController.updateBook(book4);

        MatcherAssert.assertThat(bookRes.getName(), Matchers.equalTo(book4.getName()));

    }

    //endregion

    //region service

    @Test
    void getBooksService() {
        //With
        ArrayList<Book> BookList = new ArrayList<>(Arrays.asList(book1, book2, book3));

        BookRepository BookRepository = getBookRepository();

        //When
        Mockito.when(BookRepository.findAll()).thenReturn(BookList);
        ArrayList<Book> bookResult = this.bookService.getBooks();

        //Then
        MatcherAssert.assertThat(bookResult.get(0).getName(), Matchers.equalTo(BookList.get(0).getName()));

    }

    @Test
    void setBookService() {

        Book book4 = new Book(4, "name4",  "direction4", 42);

        BookRepository BookRepository = getBookRepository();

        Mockito.when(BookRepository.save(book4)).thenReturn(book4);

        Book bookRes = this.bookService.setBook(book4);

        MatcherAssert.assertThat(bookRes.getName(), Matchers.equalTo(book4.getName()));

    }

    @Test
    void getBookByIdService() {
        Book book4 = new Book(4, "name4", "last name4",  42);
        BookRepository BookRepository = getBookRepository();
        Mockito.when(BookRepository.save(book4)).thenReturn(book4);

        Optional<Book> opbook = Optional.of(book4);
        Mockito.when((BookRepository.findById(4))).thenReturn(opbook);

        Book bookRes = this.bookService.setBook(book4);

        MatcherAssert.assertThat(bookRes, Matchers.not(Matchers.nullValue()));

    }

    @Test
    void getByIdService() {
        Book book4 = new Book(4, "name4", "last name4",  42);
        BookRepository BookRepository = getBookRepository();
        Mockito.when(BookRepository.save(book4)).thenReturn(book4);

        Optional<Book> opbook = Optional.of(book4);
        Mockito.when((BookRepository.findById(4))).thenReturn(opbook);

        Book bookRes = this.bookService.setBook(book4);

        MatcherAssert.assertThat(bookRes, Matchers.not(Matchers.nullValue()));

    }

    @Test
    void updateBookByAddingReservationService() {

    }

    @Test
    void updateBookByDeletingReservationService() {
    }

    @Test
    void deleteBookService() {
        Book book4 = new Book(4, "name4",  "direction4", 42);
        BookRepository BookRepository = getBookRepository();
        Mockito.when(BookRepository.save(book4)).thenReturn(book4);


        Mockito.doNothing().when(BookRepository).deleteById(isA(Integer.class));
        BookRepository.deleteById(4);
    }

    @Test
    void updateBookService() {
        Book book4 = new Book(0, "name4",  "direction4", 42);

        BookRepository BookRepository = getBookRepository();

        Mockito.when(BookRepository.save(book4)).thenReturn(book4);
        Book bookRes = this.bookService.setBook(book4);

        book4.setName("juanes");
        Mockito.when(BookRepository.save(book4)).thenReturn(book4);
        Book bookRes2 = this.bookService.updateBook(book4);

        MatcherAssert.assertThat(bookRes.getName(), Matchers.equalTo(book4.getName()));

    }

    @Test
    void getBooksDTOService() {
        ArrayList<Book> BookList = new ArrayList<>(Arrays.asList(book1, book2, book3));

        BookRepository BookRepository = getBookRepository();

        //When
        Mockito.when(BookRepository.findAll()).thenReturn(BookList);
        ArrayList<BookDTO> bookResult = this.bookService.getBooksDTO();

        //Then
        MatcherAssert.assertThat(bookResult.get(0).getName(), Matchers.equalTo(BookList.get(0).getName()));

    }


    //endregion

    //region repository

    @Test
    void getBooksRepository(){
        bookRepository.deleteAll();

        Book book4 = new Book(4, "name4",  "direction4", 42);

        Book aux = bookRepository.save(book4);

        ArrayList<Book> arr = (ArrayList<Book>) bookRepository.findAll();

        MatcherAssert.assertThat(arr.size(), Matchers.equalTo(1));
    }

    @Test
    void saveBookRepository(){
        bookRepository.deleteAll();
        Book book4 = new Book(4, "name4", "direction4", 42);
        Book aux = bookRepository.save(book4);
        MatcherAssert.assertThat(aux.getName(), Matchers.equalTo(book4.getName()));

    }

    //endregion

    ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper;
    }

    BookService getBookService() {
        BookService bookService = Mockito.mock(BookService.class);
        this.bookController.setBookService(bookService);
        return bookService;
    }

    BookRepository getBookRepository() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        this.bookService.setBookRepository(bookRepository);
        return bookRepository;
    }


}
