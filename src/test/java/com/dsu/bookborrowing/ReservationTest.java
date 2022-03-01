package com.dsu.bookborrowing;

import com.dsu.bookborrowing.DTO.ReservationDTO;
import com.dsu.bookborrowing.controller.ReservationController;
import com.dsu.bookborrowing.entity.Book;
import com.dsu.bookborrowing.entity.Customer;
import com.dsu.bookborrowing.entity.Reservation;
import com.dsu.bookborrowing.entity.RolModel;
import com.dsu.bookborrowing.repository.BookRepository;
import com.dsu.bookborrowing.repository.CustomerRepository;
import com.dsu.bookborrowing.repository.ReservationRepository;
import com.dsu.bookborrowing.repository.RolRepository;
import com.dsu.bookborrowing.service.BookService;
import com.dsu.bookborrowing.service.CustomerService;
import com.dsu.bookborrowing.service.ReservationService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RolRepository rolRepository;

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
    void addReservationController() {
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

        ReservationDTO reservationDTO = modelMapper.map(reservation1, ReservationDTO.class);

        ReservationService reservationMockService = Mockito.mock(ReservationService.class);
        this.reservationController.setReservationService(reservationMockService);

        // when
        Mockito.when(reservationMockService.addNewReservation(reservation1)).thenReturn(reservation1);
        ReservationDTO resultReservationDTO = this.reservationController.addReservation(reservation1);

        // then
        MatcherAssert.assertThat(resultReservationDTO.getBookId(), Matchers.equalTo(reservationDTO.getBookId()));

    }

    @Test
    void addReservationExtensionController() {
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

        ReservationDTO reservationDTO = modelMapper.map(reservation1, ReservationDTO.class);

        ReservationService reservationMockService = Mockito.mock(ReservationService.class);
        this.reservationController.setReservationService(reservationMockService);

        // when
        Mockito.when(reservationMockService.addReservationExtension(reservation1)).thenReturn(reservation1);
        ReservationDTO resultReservationDTO = this.reservationController.addReservationExtension(reservation1);

        // then
        MatcherAssert.assertThat(resultReservationDTO.getBookId(), Matchers.equalTo(reservationDTO.getBookId()));

    }

    @Test
    void addReservationReturnController() {
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

        ReservationDTO reservationDTO = modelMapper.map(reservation1, ReservationDTO.class);

        ReservationService reservationMockService = Mockito.mock(ReservationService.class);
        this.reservationController.setReservationService(reservationMockService);

        // when
        Mockito.when(reservationMockService.addReturn(reservation1)).thenReturn(reservation1);
        ReservationDTO resultReservationDTO = this.reservationController.addReservationReturn(reservation1);

        // then
        MatcherAssert.assertThat(resultReservationDTO.getBookId(), Matchers.equalTo(reservationDTO.getBookId()));
    }

    @Test
    void deleteReservationController() {
        // with
        ModelMapper modelMapper = new ModelMapper();
        if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }

        Reservation reservation = new Reservation();
        reservation.setId(1);

        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);

        ReservationService reservationMockService = Mockito.mock(ReservationService.class);
        this.reservationController.setReservationService(reservationMockService);

        // when
        Mockito.when(reservationMockService.deleteReservation(reservation)).thenReturn(reservation);
        ReservationDTO resultReservationDTO = this.reservationController.deleteReservation(reservation);

        // then
        MatcherAssert.assertThat(resultReservationDTO.getId(), Matchers.equalTo(reservationDTO.getId()));
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

    @Test
    void addReservationService() {
        // with
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setBook(new Book());
        reservation1.getBook().setBook_id(1);
        reservation1.setCustomer(new Customer());
        reservation1.setCustomer(new Customer());
        reservation1.setEstimatedDate(LocalDate.now().plusDays(4));


        ReservationRepository reservationMockRepository = Mockito.mock(ReservationRepository.class);
        BookService bookMockService = Mockito.mock(BookService.class);
        CustomerService customerMockService = Mockito.mock(CustomerService.class);
        this.reservationService.setReservationRepository(reservationMockRepository);
        this.reservationService.setBookService(bookMockService);
        this.reservationService.setCustomerService(customerMockService);

        //when
        Mockito.when(bookMockService.getBookById(reservation1.getBook().getBook_id())).thenReturn(Optional.of(reservation1.getBook()));
        Mockito.when(customerMockService.getById(reservation1.getCustomer().getId())).thenReturn(Optional.of(reservation1.getCustomer()));
        Mockito.when(reservationMockRepository.findByCustomerIdAndStatusNot(reservation1.getId(), 4)).thenReturn(new ArrayList<>());
        Mockito.when(bookMockService.updateBookByAddingReservation(reservation1.getBook().getBook_id())).thenReturn(true);
        Mockito.when(reservationMockRepository.save(reservation1)).thenReturn(reservation1);

        Reservation resultReservation = this.reservationService.addNewReservation(reservation1);

        //then
        MatcherAssert.assertThat(resultReservation.getId(), Matchers.equalTo(reservation1.getId()));
    }

    @Test
    void addReservationExtensionService() {
        // with
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setEstimatedDate(LocalDate.now().plusDays(10));

        Reservation reservation2 = new Reservation();
        reservation2.setId(1);
        reservation2.setBook(new Book());
        reservation2.getBook().setBook_id(1);
        reservation2.setCustomer(new Customer());
        reservation2.setCustomer(new Customer());
        reservation2.setEstimatedDate(LocalDate.now().plusDays(4));

        ReservationRepository reservationMockRepository = Mockito.mock(ReservationRepository.class);
        this.reservationService.setReservationRepository(reservationMockRepository);

        //when
        Mockito.when(reservationMockRepository.getById(reservation1.getId())).thenReturn(reservation2);
        Mockito.when(reservationMockRepository.save(reservation2)).thenReturn(reservation2);

        Reservation resultReservation = this.reservationService.addReservationExtension(reservation1);

        //then
        MatcherAssert.assertThat(resultReservation.getId(), Matchers.equalTo(reservation1.getId()));
        MatcherAssert.assertThat(resultReservation.getStatus(), Matchers.equalTo(1));
    }

    @Test
    void addReservationReturnService() {
        // with
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);
        reservation1.setBook(new Book());
        reservation1.getBook().setBook_id(1);
        reservation1.setCustomer(new Customer());
        reservation1.setCustomer(new Customer());
        reservation1.setEstimatedDate(LocalDate.now().plusDays(4));

        ReservationRepository reservationMockRepository = Mockito.mock(ReservationRepository.class);
        this.reservationService.setReservationRepository(reservationMockRepository);

        //when
        Mockito.when(reservationMockRepository.getById(reservation1.getId())).thenReturn(reservation1);
        Mockito.when(reservationMockRepository.save(reservation1)).thenReturn(reservation1);

        Reservation resultReservation = this.reservationService.addReturn(reservation1);

        //then
        MatcherAssert.assertThat(resultReservation.getId(), Matchers.equalTo(reservation1.getId()));
        MatcherAssert.assertThat(resultReservation.getStatus(), Matchers.equalTo(4));
    }

    @Test
    void deleteReservationService() {
        // with
        Reservation reservation1 = new Reservation();
        reservation1.setId(1);

        ReservationRepository reservationMockRepository = Mockito.mock(ReservationRepository.class);
        this.reservationService.setReservationRepository(reservationMockRepository);

        //when
        Reservation resultReservation = this.reservationService.deleteReservation(reservation1);

        //then
        MatcherAssert.assertThat(resultReservation.getId(), Matchers.equalTo(reservation1.getId()));
    }

    @Test
    void addReservationRepository() {
        // with
        RolModel rol = new RolModel();
        rol.setId(1);
        rol.setRol("Test Rol");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test User");
        customer.setUsername("test_user");
        customer.setRol(rol);

        Book book = new Book();
        book.setBook_id(1);
        book.setName("Test Book");

        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setBook(book);
        reservation.setCustomer(customer);

        // when
        rolRepository.save(rol);
        customerRepository.save(customer);
        bookRepository.save(book);
        reservationRepository.save(reservation);

        Reservation resultReservation = reservationRepository.getById(reservation.getId());

        // then
        MatcherAssert.assertThat(resultReservation.getId(), Matchers.equalTo(reservation.getId()));
    }

    @Test
    void deleteReservationRepository() {
        // with
        RolModel rol = new RolModel();
        rol.setId(1);
        rol.setRol("Test Rol");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test User");
        customer.setUsername("test_user");
        customer.setRol(rol);

        Book book = new Book();
        book.setBook_id(1);
        book.setName("Test Book");

        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setBook(book);
        reservation.setCustomer(customer);

        // when
        rolRepository.save(rol);
        customerRepository.save(customer);
        bookRepository.save(book);
        reservationRepository.save(reservation);

        reservationRepository.delete(reservation);

        Reservation resultReservation = reservationRepository.getById(reservation.getId());

        System.out.println(resultReservation);
        // then
        MatcherAssert.assertThat(resultReservation, Matchers.is(Matchers.nullValue()));
    }

}
