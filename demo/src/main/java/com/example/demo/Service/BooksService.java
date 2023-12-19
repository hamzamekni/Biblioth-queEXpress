package com.example.demo.Service;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.User;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface BooksService {
    void saveBook(BooksDto booksDto);

    Books findById(Long Id);

    List<BooksDto> findAllBooks();

    void deleteBook(Long id);
}
