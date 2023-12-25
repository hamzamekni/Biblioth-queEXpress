package com.example.demo.Controller;

import org.springframework.ui.Model;
import com.example.demo.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StatistiqueController {
    private final BooksService bookService;

    @Autowired
    public StatistiqueController(BooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/statistique")
    public String statistiqueForm(Model model) {
        // Fetch category statistics from the service
        Map<String, Long> categoryStatistics = bookService.getCategoryStatistics();

        // Log the categoryStatistics for debugging
        System.out.println("Category Statistics: " + categoryStatistics);

        // Pass the statistics to the view
        model.addAttribute("categoryStatistics", categoryStatistics);

        return "statistique";
    }

}
