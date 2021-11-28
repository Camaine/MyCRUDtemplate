package com.greenspring.green.repo;

import com.greenspring.green.model.CodeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface CodeTableRepository extends JpaRepository<CodeTable, Integer> {
    Optional<CodeTable> findByCodeNameEquals(String codeName);

}
