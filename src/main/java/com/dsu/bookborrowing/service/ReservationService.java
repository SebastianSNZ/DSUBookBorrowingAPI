package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.repository.ReservationRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Setter
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;


    public List<Reservation> getReservations() {
        List<Reservation> reservationsToUpdate = reservationRepository.findByStatusNotAndStatusNot(3, 4);
        for (Reservation reservation : reservationsToUpdate) {
            updateReservationStatus(reservation);
        }
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(Reservation reservation) {
        //Getting the book and the customer information
        Optional<Customer> customer = customerService.getById(reservation.getCustomer().getId());
        Optional<Book> book = bookService.getBookById(reservation.getBook().getBook_id());
        reservation.setCustomer(customer.orElse(null));
        reservation.setBook(book.orElse(null));
        if (reservation.getCustomer() == null || reservation.getBook() == null) {
            throw new IllegalStateException("You have to provide a valid book or customer id.");
        }
        List<Reservation> customerReservations;
        customerReservations = reservationRepository.findByCustomerIdAndStatusNot(reservation.getCustomer().getId(), 4);
        if (customerReservations.size() > 2) {
            throw new IllegalStateException("Customer doesn't have any reservations available.");
        }
        if (!bookService.updateBookByAddingReservation(reservation.getBook().getBook_id())) {
            throw new IllegalStateException("Book is not available.");
        }
        if (!reservationEstimatedDayIsValid(reservation.getReservationDate(), reservation.getEstimatedDate())) {
            throw new IllegalStateException("Estimated date is not valid.");
        }

        return reservationRepository.save(reservation);
    }

    public Reservation addReservationExtension(Reservation reservation){
        Reservation reservationToUpdate = reservationRepository.getById(reservation.getId());
        if (reservationToUpdate.getStatus() != 0 && reservationToUpdate.getStatus() != 1) {
            throw new IllegalStateException("You can't ask for an extension to this reservation.");
        }
        if (!reservationEstimatedDayIsValid(reservationToUpdate.getEstimatedDate(), reservation.getEstimatedDate())) {
            throw new IllegalStateException("New estimated date is not valid.");
        }
        reservationToUpdate.setStatus(reservationToUpdate.getStatus() + 1);
        reservationToUpdate.setEstimatedDate(reservation.getEstimatedDate());
        return reservationRepository.save(reservationToUpdate);
    }

    public Reservation addReturn(Reservation reservation) {
        Reservation reservationToUpdate = reservationRepository.getById(reservation.getId());
        if (reservationToUpdate.getStatus() == 4) {
            throw new IllegalStateException("This book has been returned.");
        }
        reservationToUpdate.setStatus(4);
        reservationToUpdate.setReturnDate(LocalDate.now());
        bookService.updateBookByDeletingReservation(reservation.getBook().getBook_id());
        return reservationRepository.save(reservationToUpdate);
    }

    public Reservation deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
        return reservation;
    }

    private boolean reservationEstimatedDayIsValid(LocalDate startDate, LocalDate finalDate) {
        if (startDate == null || finalDate == null) {
            return false;
        }
        int days = Period.between(startDate, finalDate).getDays();
        return days > 0 && days <= 7;
    }

    private boolean updateReservationStatus(Reservation reservation) {
        int days = Period.between(LocalDate.now(), reservation.getEstimatedDate()).getDays();
        if (days > 0) return false;
        reservation.setStatus(3);
        reservation.setPenalty(- days * 0.20);
        reservationRepository.save(reservation);
        return true;
    }
}
