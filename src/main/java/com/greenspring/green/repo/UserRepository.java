package com.greenspring.green.repo;

import com.greenspring.green.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    //JPA Naming Query

    //SELECT * FORM user WHERE username = ? and password = ?
    //User findByUsernameAndPassword(String username, String password);

    //@Query(value = "SELECT * FORM user WHERE username = ?1 and password = ?2", nativeQuery = true)
    //User login(String username, String password);

}
