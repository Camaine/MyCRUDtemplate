package com.greenspring.green.service;

import com.greenspring.green.model.User;
import com.greenspring.green.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring do component scan register to Bean, IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void userRegister(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User userLogin(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
