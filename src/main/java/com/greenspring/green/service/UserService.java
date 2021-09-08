package com.greenspring.green.service;

import com.greenspring.green.model.User;
import com.greenspring.green.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Spring do component scan register to Bean, IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void userRegister(User user){
        userRepository.save(user);
    }
}
