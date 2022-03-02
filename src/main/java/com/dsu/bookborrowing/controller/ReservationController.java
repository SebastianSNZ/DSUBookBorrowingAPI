package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.DTO.ReservationDTO;
import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.service.ReservationService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/v1/reservation")
@Setter
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ReservationDTO> getReservations() {
        log.info("Getting all reservations.");
        return reservationService.getReservations()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ReservationDTO addReservation(@RequestBody Reservation reservation) {
        log.info("Adding a new reservation.");
        return convertToDTO(reservationService.addNewReservation(reservation));
    }

    @PutMapping("/extension")
    public ReservationDTO addReservationExtension(@RequestBody Reservation reservation) {
        log.info("Adding an extension to a reservation.");
        return convertToDTO(reservationService.addReservationExtension(reservation));
    }

    @PutMapping("/return")
    public ReservationDTO addReservationReturn(@RequestBody Reservation reservation) {
        log.info("Returning a book to a reservation.");
        return convertToDTO(reservationService.addReturn(reservation));
    }

    @DeleteMapping()
    public  ReservationDTO deleteReservation(@RequestBody Reservation reservation) {
        log.info("Deleting a reservation.");
        return convertToDTO(reservationService.deleteReservation(reservation));
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
        return modelMapper.map(reservation, ReservationDTO.class);
    }

}

