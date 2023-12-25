package com.example.demo.Service.impl;

import com.example.demo.Entity.Books;
import com.example.demo.Entity.Categorie;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CategorieRepository;
import com.example.demo.Service.BooksService;
import com.example.demo.dto.BooksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BooksService {
    @Autowired
    private CategorieRepository categorieRepository;
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository, CategorieRepository categorieRepository ){
        this.bookRepository=bookRepository;
        this.categorieRepository=categorieRepository;
    }
    @Override
    public void saveBook(BooksDto booksDto) {
        Books books = new Books();

        books.setAuthor(booksDto.getAuthor());
        books.setTitle(booksDto.getTitle());
        books.setDate_pub(booksDto.getDate_pub());
        books.setAvailable(booksDto.isAvailable());
        books.setPrice(booksDto.getPrice());
        books.setIsbn_num(booksDto.getIsbn_num());
        List<Categorie> categories = booksDto.getCategorieIds().stream()
                .map(categoryId -> categorieRepository.findById(categoryId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        books.setCategories(categories);
        bookRepository.save(books);
    }

    @Override
    public Books findById(Long id) {
        Books books = bookRepository.findById(id);
        return books;
    }

    @Override
    public BooksDto findByIdd(Long Id) {
        return null;
    }


    @Override
    public List<BooksDto> findAllBooks() {
        List<Books> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDtoWithCategorieNames).collect(Collectors.toList());
    }

    private BooksDto convertEntityToDtoWithCategorieNames(Books books){
        BooksDto booksDto = convertEntityToDto(books);

        // Populate categorie names
        List<String> categorieNames = books.getCategories().stream()
                .map(Categorie::getName)
                .collect(Collectors.toList());
        booksDto.setCategorieNames(categorieNames);

        return booksDto;
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

        Books books = bookRepository.findById(id);

        if (books != null) {
            // Remove the book from categories to avoid cascading issues
            for (Categorie categorie : books.getCategories()) {
                categorie.getBooks().remove(books);
            }

            // Clear the categories from the book
            books.getCategories().clear();

            // Delete the book
            bookRepository.delete(books);
        }
    }



    @Override
    public void updateBook(Long id, BooksDto updatedBook) {
        Books books = bookRepository.findById(id);

        books.setAuthor(updatedBook.getAuthor());
        books.setTitle(updatedBook.getTitle());
        books.setAvailable(updatedBook.isAvailable());
        books.setPrice(updatedBook.getPrice());
        books.setIsbn_num(updatedBook.getIsbn_num());
        books.setDate_pub(updatedBook.getDate_pub());
        // Update categories
        System.out.println(categorieRepository.findAllById(updatedBook.getCategorieIds()));
        List<Categorie> updatedCategories = null;
        if (updatedBook.getCategorieIds() != null) {
            updatedCategories = categorieRepository.findAllById(updatedBook.getCategorieIds());
        }

        // Clear existing associations and add new ones
        books.getCategories().clear();
        if (updatedCategories != null) {
            books.getCategories().addAll(updatedCategories);
        }

        bookRepository.save(books);

    }


    @Override
    public List<BooksDto> searchBooksByTitleOrAuthor(String keyword) {
        List<Books> books = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

}