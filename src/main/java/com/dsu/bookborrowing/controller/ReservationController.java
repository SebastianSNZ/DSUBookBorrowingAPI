package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addNewReservation(reservation);
    }

    @PutMapping("/extension")
    public Reservation addReservationExtension(@RequestBody Reservation reservation) {
        return reservationService.addReservationExtension(reservation);
    }

    @PutMapping("/return")
    public Reservation addReservationReturn(@RequestBody Reservation reservation) {
        return reservationService.addReturn(reservation);
    }

    @DeleteMapping()
    public Reservation deleteReservation(@RequestBody Reservation reservation) {
        return reservationService.deleteReservation(reservation);
    }

}
