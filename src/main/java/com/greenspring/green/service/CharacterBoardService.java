package com.greenspring.green.service;

import com.greenspring.green.model.Board;
import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.model.TwtUser;
import com.greenspring.green.model.User;
import com.greenspring.green.repo.BoardRepository;
import com.greenspring.green.repo.CharacterBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Spring do component scan register to Bean, IoC
@Service
public class CharacterBoardService {

    private final CharacterBoardRepository characterBoardRepository;

    public CharacterBoardService(CharacterBoardRepository characterBoardRepository) {
        this.characterBoardRepository = characterBoardRepository;
    }

    @Transactional
    public void post(CharacterBoard characterBoard){
        characterBoard.setCount(0);
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
