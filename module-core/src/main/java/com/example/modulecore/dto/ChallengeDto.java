package com.example.modulecore.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    private Long id;
    private Long userId;
    private String challengeName;
    private int goalBooks;
    private int booksRead;
    private boolean completed;
}
