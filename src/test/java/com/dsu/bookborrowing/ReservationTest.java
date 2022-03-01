package com.dsu.bookborrowing;

import com.dsu.bookborrowing.DTO.ReservationDTO;
import com.dsu.bookborrowing.controller.ReservationController;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.repository.ReservationRepository;
import com.dsu.bookborrowing.service.ReservationService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest(classes = BookBorrowingApplication.class)
public class ReservationTest {
    @Autowired
    ReservationController reservationController;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void getReservationsController() {
        // with
        ModelMapper modelMapper = new ModelMapper();
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }

        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setBook(new Book());
        reservation1.getBook().setBook_id(1);
        reservation1.setCustomer(new Customer());

        List<Reservation> reservationList = List.of(reservation1);
        List<ReservationDTO> reservationDTOList = reservationList.stream().map(
                reservation -> modelMapper.map(reservation, ReservationDTO.class)
        ).collect(Collectors.toList());

        ReservationService reservationMockService = Mockito.mock(ReservationService.class);
        this.reservationController.setReservationService(reservationMockService);
        // when
        Mockito.when(reservationMockService.getReservations()).thenReturn(reservationList);
        List<ReservationDTO> resultReservationList = reservationController.getReservations();

        //then
        MatcherAssert.assertThat(resultReservationList.get(0).getBookId(), Matchers.equalTo(reservationDTOList.get(0).getBookId()));
    }

    @Test
    void getReservationsService() {
        // with
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setBook(new Book());
        reservation1.getBook().setBook_id(1);
        reservation1.setCustomer(new Customer());
        reservation1.setEstimatedDate(LocalDate.now().plusDays(4));

        List<Reservation> reservationList = List.of(reservation1);

        ReservationRepository reservationMockRepository = Mockito.mock(ReservationRepository.class);
        this.reservationService.setReservationRepository(reservationMockRepository);

        // when
        Mockito.when(reservationMockRepository.findByStatusNotAndStatusNot(3,4)).thenReturn(reservationList);
        Mockito.when(reservationMockRepository.findAll()).thenReturn(reservationList);
        List<Reservation> resultReservationList = reservationService.getReservations();

        // then
        MatcherAssert.assertThat(resultReservationList.get(0).getId(), Matchers.equalTo(reservationList.get(0).getId()));
    }

}
