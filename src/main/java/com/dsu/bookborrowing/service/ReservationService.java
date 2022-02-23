package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerService customerService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(Reservation reservation) {
        //reservation.getCustomer().getCustomer_id();
        //reservation.getBook().getBook_id();
        //customerService.
        return reservationRepository.save(reservation);
    }

}
