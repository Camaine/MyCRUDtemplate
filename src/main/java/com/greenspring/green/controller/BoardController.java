package com.greenspring.green.controller;

import com.greenspring.green.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @GetMapping("/")
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(/*@AuthenticationPrincipal PrincipalDetails principal*/){
        System.out.println("index");
        // /WEB-INF/views/index.jsp
        //System.out.println("login : " + principal.getUsername());
        return "index";
    }

    //need User Auth
    @GetMapping("/board/savePost")
    public String saveForm(){
        return "board/savePost";
    }
}
