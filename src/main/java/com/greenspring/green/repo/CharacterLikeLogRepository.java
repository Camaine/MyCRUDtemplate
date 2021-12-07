package com.greenspring.green.repo;

import com.greenspring.green.model.CharacterLikeLog;
import com.greenspring.green.model.ServiceLog;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface CharacterLikeLogRepository extends JpaRepository<CharacterLikeLog, Integer> {
    Optional<CharacterLikeLog> findByCharacterIdAndUid(int characterId, String uid);

}
