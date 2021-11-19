package com.greenspring.green.service;

import com.greenspring.green.model.CharacterBoard;
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
        characterBoardRepository.save(characterBoard);
    }

    @Transactional(readOnly = true)
    public List<CharacterBoard> characterList(CharacterBoard characterBoard){
        return characterBoardRepository.findByCharacterNameContainingIgnoreCaseAndCreatorNameContainingIgnoreCaseAndPrimaryColorContainingAndSecondaryColorContaining(
                characterBoard.getCharacterName(),
                characterBoard.getCreatorName(),
                characterBoard.getPrimaryColor(),
                characterBoard.getSecondaryColor()
        );
    }

    @Transactional
    public void updateCharacter(int id, CharacterBoard requestCharacterBoard){
        CharacterBoard characterBoard = characterBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        characterBoard.setCharacterName(requestCharacterBoard.getCharacterName());
        characterBoard.setBio(requestCharacterBoard.getBio());
        System.out.println("update : " + id );
    }

    @Transactional
    public void deleteCharacter(int id){
        characterBoardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<CharacterBoard> postList(Pageable pageable){
        return characterBoardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public CharacterBoard showDetail(int id){
        return characterBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void deletePost(int id){
        characterBoardRepository.deleteById(id);
    }
    @Transactional
    public void updatePost(int id, CharacterBoard requestCharacterBoard){
        CharacterBoard characterBoard = characterBoardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        characterBoard.setCharacterName(requestCharacterBoard.getCharacterName());
        characterBoard.setProfileImage(requestCharacterBoard.getProfileImage());
        System.out.println("update : " + id);
        //해당 함수 종료시 트랜젝션종료, 더티체킹이 이루어지면서 자동업데이트
    }
}
