package com.example.modulecore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @Schema(description = "사용자 닉네임 (사이트에 보여지는 이름)", example = "수민")
    private String name;

    @Schema(description = "로그인용 이메일 주소", example = "sumin@naver.com")
    private String email;

    @Schema(description = "로그인에 사용할 비밀번호", example = "1234")
    private String password;
}