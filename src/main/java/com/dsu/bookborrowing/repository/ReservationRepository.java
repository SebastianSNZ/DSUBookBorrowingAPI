package com.dsu.bookborrowing.repository;

import com.dsu.bookborrowing.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByCustomerIdAndStatusNot(Integer id, Integer status);
    List<Reservation> findByStatusNotAndStatusNot(Integer fistStatus, Integer secondStatus);
}
