package com.dsu.bookborrowing;

import com.dsu.bookborrowing.controller.ReservationController;
import com.dsu.bookborrowing.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = BookBorrowingApplication.class)
class BookBorrowingApplicationTests {

	@Autowired
	ReservationController reservationController;

	@Autowired
	ReservationService reservationService;

	@Test
	void contextLoads() {
	}

}
