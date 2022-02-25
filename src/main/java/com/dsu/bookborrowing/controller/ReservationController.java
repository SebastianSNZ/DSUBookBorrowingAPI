package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.ReservationDTO;
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
    public List<ReservationDTO> getReservations() {
        return ReservationDTO.convertListToDTO(reservationService.getReservations());
    }

    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO reservationDTO) {
        return new ReservationDTO(reservationService.addNewReservation(new Reservation(reservationDTO)));
    }

    @PutMapping("/extension")
    public ReservationDTO addReservationExtension(@RequestBody ReservationDTO reservationDTO) {
        return new ReservationDTO(reservationService.addReservationExtension(new Reservation(reservationDTO)));
    }

    @PutMapping("/return")
    public ReservationDTO addReservationReturn(@RequestBody ReservationDTO reservationDTO) {
        return new ReservationDTO(reservationService.addReturn(new Reservation(reservationDTO)));
    }

    @DeleteMapping()
    public  ReservationDTO deleteReservation(@RequestBody ReservationDTO reservationDTO) {
        return new ReservationDTO(reservationService.deleteReservation(new Reservation(reservationDTO)));
    }

}

