package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface BooksService {
    void saveBook(BooksDto booksDto);

    User findById(String Id);

    List<BooksDto> findAllBooks();

    void deleteBook(Long id);
}
