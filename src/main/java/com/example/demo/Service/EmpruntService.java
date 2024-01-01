package com.example.demo.Service;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;

public interface EmpruntService {
    void empruntBook(User user, Books book, LocalDate dateDebut,LocalDate dateFin) ;
    void renevolerBook(User user, Books book,LocalDate dateFin) ;
}
