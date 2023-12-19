package com.example.demo.Repository;

import com.example.demo.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {
    Optional<Books> findById(Long Id);
}
