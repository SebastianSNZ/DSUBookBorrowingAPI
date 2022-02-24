package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final BookService bookService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerService customerService, BookService bookService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(Reservation reservation) {
        //Getting the book and the customer information
        Optional<Customer> customer = customerService.getById(reservation.getCustomer().getCustomer_id());
        Optional<Book> book = bookService.getBookById(reservation.getBook().getBook_id());
        reservation.setCustomer(customer.isPresent() ? customer.get() : null);
        reservation.setBook(book.isPresent() ? book.get(): null);

        if (reservation.getCustomer() == null || reservation.getBook() == null) {
            throw new IllegalStateException("You have to provide a valid book or customer id.");
        }
        if (!bookService.updateBookByAddingReservation(reservation.getBook().getBook_id())) {
            throw new IllegalStateException("Book is not available.");
        }
        if (!reservationEstimatedDayIsValid(reservation.getReservationDate(), reservation.getEstimatedDate())) {
            throw new IllegalStateException("Estimated date is not valid.");
        }
        return reservationRepository.save(reservation);
    }

    private boolean reservationEstimatedDayIsValid(LocalDate startDate, LocalDate finalDate) {
        if (finalDate == null) {
            return false;
        }
        int days = Period.between(startDate, finalDate).getDays();
        return days > 0 && days <= 7;
    }

}
