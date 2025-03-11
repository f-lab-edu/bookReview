package com.example.moduleapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db-test")
public class DatabaseTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check")
    public ResponseEntity<String> checkDatabaseConnection() {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            return ResponseEntity.ok("users 테이블 행 개수: " + count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("DB 연결 실패: " + e.getMessage());
        }
    }
}
