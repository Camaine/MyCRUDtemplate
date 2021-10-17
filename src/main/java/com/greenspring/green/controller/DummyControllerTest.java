package com.greenspring.green.controller;

import com.greenspring.green.model.RoleType;
import com.greenspring.green.model.User;
import com.greenspring.green.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;
/*
    //id 주소로 Parameter를 전달 받을 수 있
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });
        return user;
    }*/

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 1, sort="id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch(Exception e){
            return "Delete Failed";
        }
        return "delete :" + id;
    }

    //email, password
    @Transactional
    @PutMapping("dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id :" + id);
        System.out.println("pass : " +requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Edit Failed");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());


        //더티체킹
        return null;
    }

    //http://localhost:8000/green/dummy/join(REQ)
    //http 의 body에 username, password,email 데이터를 가지고 (REQ)
    @PostMapping("/dummy/join")
    public String join(User user){ // key = value
        System.out.println("id:" + user.getId());
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        System.out.println("email:" + user.getEmail());
        System.out.println("role:" + user.getRole());
        System.out.println("createDate:" + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "Account Register Complete";
    }

}
