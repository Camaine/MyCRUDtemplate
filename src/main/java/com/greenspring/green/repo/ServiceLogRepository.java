package com.greenspring.green.repo;

import com.greenspring.green.model.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface ServiceLogRepository extends JpaRepository<ServiceLog, Integer> {

}
