package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable=false)
    private String categories;

}
