package com.example.demo.Service;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;

public interface ReservationService {
    void reserveBook(User user, Books book) ;
}
