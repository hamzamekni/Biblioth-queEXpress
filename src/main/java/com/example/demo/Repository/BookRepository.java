package com.example.demo.Repository;

import com.example.demo.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, String> {
    Books findById(Long id);
    void deleteById(Long id);
    List<Books> findBycategories(String categories);

}
