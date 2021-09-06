package com.greenspring.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {


    @GetMapping("/")
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        System.out.println("index");
        // /WEB-INF/views/index.jsp
        return "index";
    }
}
