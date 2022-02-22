package com.dsu.bookborrowing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BookBorrowingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBorrowingApplication.class, args);
	}

}
