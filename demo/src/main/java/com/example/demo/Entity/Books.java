package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, unique=true)
    private Long id;

    @Column(nullable=false)
    private String author;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String date_pub;
    @Column(nullable=false)
    private Double price;
    @Column(nullable=false)
    private boolean available;
    @Column(nullable=false)
    private int isbn_num;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_categories",
            joinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "ID")})
    private List<Categorie> categories = new ArrayList<>();

}