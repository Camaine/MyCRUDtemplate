package com.greenspring.green;

import com.greenspring.green.model.TwitterToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GreenApplicationTests {

    @Autowired
    private TwitterToken twitterToken;

    @Test
    void contextLoads() {
    }

    @Test
    void getTwitterToken(){
        System.out.println(twitterToken.getKey());
    }


}
