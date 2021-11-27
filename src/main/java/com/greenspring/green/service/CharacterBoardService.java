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

    @Transactional(readOnly = true)
    public List<CharacterBoard> characterList(CharacterBoard characterBoard){
        return characterBoardRepository.findByStatusEqualsAndOwnerUidContainingIgnoreCaseAndCharacterNameContainingIgnoreCaseAndCreatorNameContainingIgnoreCaseAndPrimaryColorContainingAndSecondaryColorContaining(
                0,
                characterBoard.getOwnerUid(),
                characterBoard.getCharacterName(),
                characterBoard.getCreatorName(),
                characterBoard.getPrimaryColor(),
                characterBoard.getSecondaryColor()
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
