package com.greenspring.green.repo;

import com.greenspring.green.model.CharacterBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// DAO
// Automatically Register Bean
// @Repository 생략 가능
public interface CharacterBoardRepository extends JpaRepository<CharacterBoard, Integer> {

    List<CharacterBoard> findByCharacterNameContainingIgnoreCaseAndCreatorNameContainingIgnoreCaseAndPrimaryColorContainingAndSecondaryColorContaining(String characterName, String creatorName, String primaryColor, String secondaryColor);
    //JPA Naming Query


}