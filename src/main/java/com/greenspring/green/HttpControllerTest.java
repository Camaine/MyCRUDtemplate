package com.greenspring.green;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {


    private static final String TAG = "HttpControllerTest :";
    //Browser request only for GET
    //http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "Get Request :" + m.getId();
    }

    //http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest(){

        return "Post Request";
    }

    //http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest(){
        return "Put Request";
    }

    //http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "Delete Request";
    }

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m1 = new Member(1,"user","P!ssw0rd","gmail");
        return "";
    }
}
