package com.example.demo.Service;

import com.example.demo.Entity.Books;
import com.example.demo.dto.BooksDto;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    void saveBook(BooksDto booksDto);

    Books findById(Long id);
    BooksDto findByIdd(Long Id);

    List<BooksDto> findAllBooks();

    void deleteBook(Long id);

    void updateBook(Long id, BooksDto updatedBook);
    List<Books> getBooksByCategory(String category);
}
