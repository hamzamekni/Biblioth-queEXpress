package com.example.demo.Controller;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Categorie;
import com.example.demo.Repository.CategorieRepository;
import com.example.demo.Service.BooksService;
import com.example.demo.Service.CategorieService;
import com.example.demo.Service.impl.CategorieServiceImpl;
import com.example.demo.dto.BooksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@Controller
public class BooksController {
    private BooksService booksService;
    @Autowired
    private CategorieServiceImpl categorieService;
    @Autowired
    private CategorieRepository categorieRepository;

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    public BooksController(BooksService booksService, CategorieRepository categorieRepository){
        this.booksService = booksService;
        this.categorieRepository=categorieRepository;

    }
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
        List<Categorie> allCategories = categorieRepository.findAll();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("books", booksDto);
        return "addBook";
    }

    @GetMapping("/Book/{id}")
    public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
        Books book = booksService.findById(id);
        List<Categorie> allCategories = categorieService.getAllCategories();
        model.addAttribute("book", book);
        model.addAttribute("allCategories", allCategories);
        return "updateBook";
    }
    @PreAuthorize("hasRole('BIBLIOTHECAIRE')")
    @PostMapping("/updateBooks")
    public String updateBook(@ModelAttribute("book") BooksDto updatedBook, @RequestParam(name = "selectedCategories", required = false) List<Long> selectedCategories, RedirectAttributes redirectAttributes) {
        Long id = updatedBook.getId();
        updatedBook.setCategorieIds(selectedCategories);
        booksService.updateBook(id, updatedBook);
        redirectAttributes.addFlashAttribute("updateSuccess", true);
        return "redirect:/books";
    }
}
