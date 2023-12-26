package com.example.demo.Controller;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;
import com.example.demo.Service.BooksService;
import com.example.demo.Service.ReservationService;
import com.example.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService; // Injectez le service d'utilisateur
    private final BooksService bookService; // Injectez le service de livre
    public ReservationController(ReservationService reservationService, UserService userService, BooksService bookService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            User user = userService.findById(userId);
            Books book = bookService.findById(bookId);


            reservationService.reserveBook(user, book);
            return ResponseEntity.ok("Book reserved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error reserving book: " + e.getMessage());
        }
    }
}
