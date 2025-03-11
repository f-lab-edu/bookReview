package com.example.modulecore.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false) // 존재하지 않는 필드를 포함하면 예외 발생
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
