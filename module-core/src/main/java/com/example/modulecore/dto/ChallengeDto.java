package com.example.modulecore.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    @Null(message = "새로운 챌린지를 생성할 때 id를 포함하면 안 됨")
    private Long id;

    @NotNull(message = "userId는 필수 값")
    private Long userId;

    private String challengeName;
    private int goalBooks;
    private int booksRead;
    private boolean completed;
}
