package com.greenspring.green.repo;

import com.greenspring.green.model.Board;
import com.greenspring.green.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //JPA Naming Query

}
