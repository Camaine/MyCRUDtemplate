package com.greenspring.green.repo;

import com.greenspring.green.model.CharacterBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface CharacterBoardRepository extends JpaRepository<CharacterBoard, Integer> {

    List<CharacterBoard> findByOwnerUidContainingIgnoreCaseAndCharacterNameContainingIgnoreCaseAndCreatorNameContainingIgnoreCaseAndPrimaryColorContainingAndSecondaryColorContaining(String ownerUid, String characterName, String creatorName, String primaryColor, String secondaryColor);

    @Query("select max(c.id) from CharacterBoard c")
    int maxId();
    //JPA Naming Query



}
