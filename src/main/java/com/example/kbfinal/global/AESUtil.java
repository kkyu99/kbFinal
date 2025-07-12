package com.example.kbfinal.global;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AESUtil {
    private static final String SECRET_KEY = "1234567890123456"; // 반드시 16자리
    private static final String ALGORITHM = "AES";

    public static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM));
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    }

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM));
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}

