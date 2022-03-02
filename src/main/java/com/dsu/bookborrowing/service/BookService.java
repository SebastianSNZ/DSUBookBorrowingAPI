package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.DTO.BookDTO;
import com.dsu.bookborrowing.DTO.ReservationDTO;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) bookRepository.findAll();
    }

    public Book setBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Integer id) {
       return bookRepository.findById(id);
    }
    public Optional<Book> getById(Integer id) {
        return bookRepository.findById(id);
    }

    public boolean updateBookByAddingReservation(Integer id) {
        Optional<Book> bookOptional = getBookById(id);
        if (bookOptional.isEmpty()) {
            return false;
        }
        Book book = bookOptional.get();
        if (book.getQuantity() < 1) {
            return false;
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        return true;
    }

    public void updateBookByDeletingReservation(Integer id) {
        Optional<Book> bookOptional = getBookById(id);
        if (bookOptional.isEmpty()) {
            return;
        }
        Book book = bookOptional.get();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }



    public boolean deleteBook(Integer id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


    public Book updateBook(Book book) {
        if (book.getBook_id() == null || !bookRepository.existsById(book.getBook_id()))
            return null;
        return bookRepository.save(book);
    }

    public ArrayList<BookDTO> getBooksDTO() {
        ArrayList<Book> arrBook = (ArrayList<Book>) bookRepository.findAll();
        ArrayList<BookDTO> arr = new ArrayList<>(arrBook.size());
        for (Book bk : arrBook)
            arr.add(converToDTO(bk));
        return arr;
    }


    public BookDTO converToDTO(Book bk){
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(bk, BookDTO.class);
    }

}
