package com.dsu.bookborrowing.DTO;

import com.dsu.bookborrowing.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private Integer customerId;
    private String customerName;
    private LocalDate reservationDate;
    private LocalDate estimatedDate;
    private LocalDate returnDate;
    private Double penalty;
    private Integer status;

}
