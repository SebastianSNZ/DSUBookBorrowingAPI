package com.dsu.bookborrowing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BookBorrowingApplication {

	private static final Logger logger = LoggerFactory.getLogger(BookBorrowingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookBorrowingApplication.class, args);

		logger.info("this is an info message");
		logger.warn("This is a warm message");
		logger.error("This is an error message");
		logger.debug("This is a debug message");

	}
}
