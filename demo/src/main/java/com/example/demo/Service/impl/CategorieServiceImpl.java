package com.example.demo.Service.impl;

import com.example.demo.Entity.Categorie;
import com.example.demo.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategorieServiceImpl {
    @Autowired
    private CategorieRepository categorieRepository;

    // Other methods...

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
