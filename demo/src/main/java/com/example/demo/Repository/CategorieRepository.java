package com.example.demo.Repository;

import com.example.demo.Entity.Categorie;
import com.example.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByName(String name);
}
