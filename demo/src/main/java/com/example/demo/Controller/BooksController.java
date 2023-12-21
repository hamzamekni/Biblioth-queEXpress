package com.example.demo.Controller;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;
import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class BooksController {
    private BooksService booksService;

    public BooksController(BooksService booksService){ this.booksService = booksService; }
    @GetMapping("/books")
    public String listRegisteredBooks(@RequestParam(name = "search", required = false) String search,
                                      Model model) {
        List<BooksDto> books;

        if (search != null && !search.isEmpty()) {
            // If search parameter is present, perform search
            books = booksService.searchBooksByTitleOrAuthor(search);
        } else {
            // Otherwise, list all books
            books = booksService.findAllBooks();
        }

        model.addAttribute("books", books);
        model.addAttribute("search", search);

        return "books";
    }

    @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
    @RequestMapping(value = "/books/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String  deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    // delete book from DB
        booksService.deleteBook(id);
        redirectAttributes.addFlashAttribute("deleteSuccess", true);
        return "redirect:/books";
    }
    @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("books") BooksDto book,
                               BindingResult result,
                               Model model){

        if (result.hasErrors()) {
            model.addAttribute("books", book);
            return "addBook";
        }
        booksService.saveBook(book);
        return "redirect:/addBook?success";
    }
    @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
    @GetMapping("/addBook")
    public String showAddBookForm(Model model){
        BooksDto booksDto = new BooksDto();
        model.addAttribute("books", booksDto);
        return "addBook";
    }

    @GetMapping("/Book/{id}")
    public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
        Books book = booksService.findById(id);
        model.addAttribute("book", book);
        return "updateBook";
    }
    @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
    @PostMapping("/updateBooks")
    public String updateBook(@ModelAttribute("book") BooksDto updatedBook, RedirectAttributes redirectAttributes) {
        // Extract the ID from the updatedBook if needed
        Long id = updatedBook.getId();
        booksService.updateBook(id, updatedBook);
        redirectAttributes.addFlashAttribute("updateSuccess", true);
        return "redirect:/books";
    }
}
