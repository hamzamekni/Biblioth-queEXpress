package com.example.demo.Service.impl;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Emprunt;
import com.example.demo.Entity.Reservation;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.EmpruntRepository;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmpruntServiceImpl implements EmpruntService {
    private final EmpruntRepository empruntRepository;
    @Autowired
    public BookRepository bookRepository ;
    @Autowired
    public EmpruntServiceImpl(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public void empruntBook(User user, Books book, LocalDate dateDebut,LocalDate dateFin) {
        if (!book.isAvailable()) {
            throw new RuntimeException("The book is not available for emprunting.");
        }
        LocalDateTime now = LocalDateTime.now();
        Emprunt emprunt = new Emprunt();
        emprunt.setUser(user);
        emprunt.setBook(book);
        emprunt.setDateDebut(dateDebut);
        emprunt.setDateFin(dateFin);

        book.setAvailable(false);

        empruntRepository.save(emprunt);
        bookRepository.save(book);
    }
    public void renevolerBook(User user, Books book, LocalDate dateFin) {
        if (!book.isAvailable()) {
            throw new RuntimeException("The book is not available for renevoler.");
        }
        LocalDate now = LocalDate.now();
        Emprunt emprunt = empruntRepository.getOneByUserIdAndBookId(user.getId(),book.getId());
        emprunt.setUser(user);
        emprunt.setBook(book);
        emprunt.setDateFin(dateFin);
        emprunt.setDateDebut(now);
        book.setAvailable(false);
        empruntRepository.save(emprunt);
        bookRepository.save(book);
    }
    public void Stats(){
        long countEmprunts = empruntRepository.count();
    }

}
