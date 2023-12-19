package com.example.demo.Controller;

import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class MemberBooksController {

    private BooksService booksService;

    public MemberBooksController(BooksService booksService){ this.booksService = booksService; }
    @GetMapping("/memberBooks")
    public String listRegisteredBooks(Model model){
        List<BooksDto> books = booksService.findAllBooks();
        model.addAttribute("memberBooks", books);
        return "memberBooks";
    }
}
