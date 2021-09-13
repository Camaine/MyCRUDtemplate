package com.greenspring.green;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

    @Test
    public void hashEncrypt(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println(encPassword);
    }
}
