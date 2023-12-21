package com.example.demo.Controller;

import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MemberBooksController {

    private BooksService booksService;

    public MemberBooksController(BooksService booksService){ this.booksService = booksService; }
    @GetMapping("/memberBooks")
    public String listMemberBooks(@RequestParam(name = "search", required = false) String search,
                                  Model model){
        List<BooksDto> memberBooks;

        if (search != null && !search.isEmpty()) {
            // If search parameter is present, perform search
            memberBooks = booksService.searchBooksByTitleOrAuthor(search);
        } else {
            // Otherwise, list all member books
            memberBooks = booksService.findAllBooks();
        }

        model.addAttribute("memberBooks", memberBooks);
        model.addAttribute("search", search);

        return "memberBooks";
    }

}
