package com.example.demo.Repository;

import com.example.demo.Entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, String> {
    Emprunt getOneByUserIdAndBookId(Long userId, Long bookId);
    long count();
    //Book findMostEmpruntedBook();
}
