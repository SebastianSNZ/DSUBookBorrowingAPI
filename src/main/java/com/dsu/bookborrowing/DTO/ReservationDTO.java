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

    public ReservationDTO(Reservation reservation) {
        this.setId(reservation.getId());
        this.setBookId(reservation.getBook().getBook_id());
        this.setBookName(reservation.getBook().getName());
        this.setCustomerId(reservation.getCustomer().getId());
        this.setCustomerName(reservation.getCustomer().getName());
        this.setReservationDate(reservation.getReservationDate());
        this.setEstimatedDate(reservation.getEstimatedDate());
        this.setReturnDate(reservation.getReturnDate());
        this.setPenalty(reservation.getPenalty());
        this.setStatus(reservation.getStatus());
    }

    public static List<ReservationDTO> convertListToDTO(List<Reservation> reservationList) {
        List<ReservationDTO> dtoList = new ArrayList<>();
        for (Reservation reservation: reservationList) {
            dtoList.add(new ReservationDTO(reservation));
        }
        return dtoList;
    }


}
