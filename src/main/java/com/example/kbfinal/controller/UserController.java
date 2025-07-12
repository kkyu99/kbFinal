package com.example.kbfinal.controller;

import com.example.kbfinal.entity.User;
import com.example.kbfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // user 정보를 입력, 삭제, 수정하는 API 생성
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id ,
                                        @RequestBody User userInfo) {
        User updatedUser = userService.updateUser(id,userInfo);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 전체 user List를 조회하는 api 생성
    @GetMapping
    public ResponseEntity<?> getUserList() {
        List<User> useList  = userService.getUserList();
        return new ResponseEntity<>(useList, HttpStatus.OK);
    }

    // 전체 user 의 숫자를 조회하는 api 생성
    @GetMapping("/count")
    public ResponseEntity<?> getUserCount() {
        Long count = userService.getUserCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
