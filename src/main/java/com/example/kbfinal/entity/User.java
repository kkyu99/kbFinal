package com.example.kbfinal.entity;

import jakarta.persistence.*;
import lombok.Data;

// lombok 사용할것
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // 추가로 3개의 attribute 를 만들기
    private String gender;
    private String email;
    private String phone;

    public void updateUserInfo(User userInfo) {
        this.username = userInfo.getUsername();
        this.gender = userInfo.getGender();
        this.email = userInfo.getEmail();
        this.phone = userInfo.getPhone();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
