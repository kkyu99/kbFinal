package com.example.kbfinal.service;

import com.example.kbfinal.entity.User;
import com.example.kbfinal.exception.CustomException;
import com.example.kbfinal.exception.ErrorCode;
import com.example.kbfinal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // 비밀번호를 암호화하여 저장
        // password를 인코딩
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(AESUtil.encrypt(user.getPassword()));

        // user entity에 인코딩 된 password를 넣기
        userRepository.save(user);
    }

   public boolean authenticate(String username, String password) {
       // 사용자 조회
       User user = userRepository.findByUsername(username); // 직접 repo에서 구현
       if (user == null) {
           throw new CustomException(ErrorCode.USER_NOT_FOUND);
       }
       // 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교
       return passwordEncoder.matches(password, user.getPassword());

   }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userInfo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.updateUserInfo(userInfo);
        if(userInfo.getPassword() != null && !userInfo.getPassword().isEmpty()) {
            user.updatePassword(passwordEncoder.encode(userInfo.getPassword()));
        }
        return userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public Long getUserCount() {
        return userRepository.count();
    }

    // 이후 컨트롤러에서 들어오게 될  내용 추가 구현하기
}
