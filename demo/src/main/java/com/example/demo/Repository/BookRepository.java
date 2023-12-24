package com.example.demo.Repository;

import com.example.demo.Entity.Books;
import com.example.demo.dto.BooksDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Books, String> {
    Books findById(Long id);
    void deleteById(Long id);
    List<Books> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);


}
