package com.greenspring.green.repo;

import com.greenspring.green.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {


}
