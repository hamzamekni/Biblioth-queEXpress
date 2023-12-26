package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private Long id;
    @NotEmpty
    private String author;
    @NotEmpty
    private String title;
    @NotEmpty
    @DateTimeFormat
    private String date_pub;
    @NotEmpty
    private Double price;
    @NotEmpty
    private boolean available;
    @NotEmpty
    private int isbn_num;
}
