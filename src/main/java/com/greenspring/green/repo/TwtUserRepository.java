package com.greenspring.green.repo;

import com.greenspring.green.model.TwtUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface TwtUserRepository extends JpaRepository<TwtUser, String>{
    Optional<TwtUser> findByUid(String uid);
}
