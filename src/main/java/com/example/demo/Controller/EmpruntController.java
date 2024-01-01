package com.example.demo.Controller;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;
import com.example.demo.Service.BooksService;
import com.example.demo.Service.EmpruntService;
import com.example.demo.Service.ReservationService;
import com.example.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;
    private final UserService userService; // Injectez le service d'utilisateur
    private final BooksService bookService; // Injectez le service de livre
    public EmpruntController(EmpruntService empruntService, UserService userService, BooksService bookService) {
        this.userService = userService;
        this.bookService = bookService;
        this.empruntService = empruntService;
    }

    @PostMapping("/emprunt")
    public ResponseEntity<String> empruntBook(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam LocalDate dateDebut,@RequestParam LocalDate dateFin) {
        try {
            User user = userService.findById(userId);
            Books book = bookService.findById(bookId);
            empruntService.empruntBook(user, book,dateDebut,dateFin);
            return ResponseEntity.ok("Book emprunted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error empruting the book: " + e.getMessage());
        }
    }
    @PostMapping("/renevoler")
    public ResponseEntity<String> renevolerBook(@RequestParam Long userId, @RequestParam Long bookId,@RequestParam LocalDate dateFin) {
        try {
            User user = userService.findById(userId);
            Books book = bookService.findById(bookId);
            empruntService.renevolerBook(user, book,dateFin);
            return ResponseEntity.ok("Book renvouler successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error renvoulement the book: " + e.getMessage());
        }
    }
}
