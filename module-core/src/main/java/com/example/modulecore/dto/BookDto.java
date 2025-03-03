package com.example.modulecore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 전체 필드를 포함하는 생성자
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private double rating;
}