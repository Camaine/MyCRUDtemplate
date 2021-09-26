package com.greenspring.green.config.auth;

import com.greenspring.green.model.User;
import com.greenspring.green.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean 등록
public class PrincipalService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //spring get login request, get username and password parameter
    //username이 db에 있는지만 확인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new PrincipalDetails(principal); // user info saved in security session
    }
}
