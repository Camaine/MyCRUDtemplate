package com.greenspring.green.controller;

import com.greenspring.green.config.auth.PrincipalDetails;
import com.greenspring.green.model.User;
import com.greenspring.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"/",""})
    public String index(Model model, @PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards", boardService.postList(pageable));
        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.showDetail(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updatePost")
     public String updatePost(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.showDetail(id));
        return "board/updatePost";
     }

    //need User Auth
    @GetMapping("/board/savePost")
    public String saveForm(){
        return "board/savePost";
    }
}
