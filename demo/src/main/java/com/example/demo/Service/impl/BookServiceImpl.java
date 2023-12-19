package com.example.demo.Service.impl;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BooksService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){ this.bookRepository=bookRepository; }
    @Override
    public void saveBook(BooksDto booksDto) {
        Books books = new Books();

        books.setAuthor(booksDto.getAuthor());
        books.setTitle(booksDto.getTitle());
        books.setDate_pub(booksDto.getDate_pub());
        books.setAvailable(booksDto.isAvailable());
        books.setPrice(booksDto.getPrice());
        books.setIsbn_num(booksDto.getIsbn_num());

        bookRepository.save(books);
    }

    @Override
    public Books findById(Long Id) {
        return null;
    }



    @Override
    public List<BooksDto> findAllBooks() {
        List<Books> books = bookRepository.findAll();
        return books.stream().map((book) -> convertEntityToDto(book))
                .collect(Collectors.toList());
    }
    private BooksDto convertEntityToDto(Books books){
        BooksDto booksDto = new BooksDto();
        booksDto.setId(books.getId());
        booksDto.setAuthor(books.getAuthor());
        booksDto.setTitle(books.getTitle());
        booksDto.setPrice(books.getPrice());
        booksDto.setDate_pub(books.getDate_pub());
        booksDto.setIsbn_num(books.getIsbn_num());
        booksDto.setAvailable(books.isAvailable());
        return booksDto;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
