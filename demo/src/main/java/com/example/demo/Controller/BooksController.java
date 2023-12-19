package com.example.demo.Controller;

import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BooksController {
    private BooksService booksService;

    public BooksController(BooksService booksService){ this.booksService = booksService; }
    @GetMapping("/books")
    public String listRegisteredBooks(Model model){
        List<BooksDto> books = booksService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
