package com.example.demo.Service.impl;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Reservation;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Service.BooksService;
import com.example.demo.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
     public BookRepository bookRepository ;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void reserveBook(User user, Books book) {
        // Check if the book is available for reservation
        if (!book.isAvailable()) {
            throw new RuntimeException("The book is not available for reservation.");
            // Alternatively, you can return an appropriate response, such as ResponseEntity.badRequest().
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDateTime.now());


        book.setAvailable(false);

        reservationRepository.save(reservation);
        bookRepository.save(book);
    }


}
