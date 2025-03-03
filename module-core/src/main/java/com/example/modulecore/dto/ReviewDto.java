package com.example.modulecore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private String content;
    private String summary;
    private double rating;
}