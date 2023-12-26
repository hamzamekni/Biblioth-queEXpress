package com.example.demo.Repository;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
