package com.greenspring.green.service;

import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.model.TwtUser;
import com.greenspring.green.repo.CharacterBoardRepository;
import com.greenspring.green.repo.TwtUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

//Spring do component scan register to Bean, IoC
@Service
public class CharacterBoardService {

    private final CharacterBoardRepository characterBoardRepository;
    private final TwtUserRepository twtUserRepository;

    public CharacterBoardService(CharacterBoardRepository characterBoardRepository, TwtUserRepository twtUserRepository) {
        this.characterBoardRepository = characterBoardRepository;
        this.twtUserRepository = twtUserRepository;
    }

    @Transactional
    public void post(CharacterBoard characterBoard){
        characterBoard.setCount(0);
        characterBoard.setOwnerName(twtUserRepository.findByUidEquals(characterBoard.getOwnerUid()).get(0).getDisplayName());
        characterBoard.setStatus(0);

        TwtUser twtUser = twtUserRepository.findByUidEquals(characterBoard.getOwnerUid()).get(0);
        twtUser.setCharacterCnt(twtUser.getCharacterCnt()+1);
        characterBoardRepository.save(characterBoard);
    }

    @Transactional
    public int postAuthCheck(CharacterBoard characterBoard){
        TwtUser twtUser = twtUserRepository.findByUidEquals(characterBoard.getOwnerUid()).get(0);
        if(Objects.equals(twtUser.getRole(), "USER") && twtUser.getCharacterCnt() >= 5){
            System.out.println("업로드 금지");
            return 2;
        }

        if(Objects.equals(twtUser.getRole(), "CREATOR") && twtUser.getCharacterCnt() >= 30){
            System.out.println("업로드 금지");
            return 1;
        }
        return 0;
    }

    @Transactional(readOnly = true)
    public List<CharacterBoard> characterList(CharacterBoard characterBoard, Pageable pageable){
        return characterBoardRepository.findByStatusEqualsAndOwnerUidContainingIgnoreCaseAndCharacterNameContainingIgnoreCaseAndCreatorNameContainingIgnoreCaseAndSpicesContainingIgnoreCaseAndPrimaryColorContainingIgnoreCaseAndSecondaryColorContainingIgnoreCase(
                0,
                characterBoard.getOwnerUid(),
                characterBoard.getCharacterName(),
                characterBoard.getCreatorName(),
                characterBoard.getSpices(),
                characterBoard.getPrimaryColor(),
                characterBoard.getSecondaryColor(),
                pageable
        );
    }

    @Transactional(readOnly = true)
    public CharacterBoard characterSingleInfo(int id){
        return characterBoardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void updateCharacter(int id, CharacterBoard requestCharacterBoard){
        CharacterBoard characterBoard = characterBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        characterBoard.setBio(requestCharacterBoard.getBio());
        characterBoard.setProfileImageUrl(requestCharacterBoard.getProfileImageUrl());
        characterBoard.setRefImageUrl(requestCharacterBoard.getRefImageUrl());
        System.out.println(characterBoard.getBio());
        System.out.println("update : " + id );
    }

    @Transactional
    public void deleteCharacter(int id){
        CharacterBoard characterBoard = characterBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        TwtUser twtUser = twtUserRepository.findByUidEquals(characterBoard.getOwnerUid()).get(0);
        twtUser.setCharacterCnt(twtUser.getCharacterCnt()-1);

        characterBoard.setStatus(2);
    }

    @Transactional(readOnly = true)
    public int getLatestCharacterId(){
        return characterBoardRepository.maxId();
    }

}
