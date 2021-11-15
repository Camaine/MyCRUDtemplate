package com.greenspring.green.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TwitterLoginController {
    @GetMapping("/auth/twtLogin")
    public String twitterLogin() {
        return "user/twtLogin";
    }
}
