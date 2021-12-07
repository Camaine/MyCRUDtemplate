package com.greenspring.green.service;

import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.model.CharacterLikeLog;
import com.greenspring.green.repo.CharacterLikeLogRepository;
import com.greenspring.green.repo.ServiceLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterLikeLogService {

    private final ServiceLogRepository serviceLogRepository;
    private final CharacterLikeLogRepository characterLikeLogRepository;

    public CharacterLikeLogService(ServiceLogRepository serviceLogRepository, CharacterLikeLogRepository characterLikeLogRepository) {
        this.serviceLogRepository = serviceLogRepository;
        this.characterLikeLogRepository = characterLikeLogRepository;
    }

    @Transactional
    public void characterLike(String uid, int cid){
        CharacterLikeLog characterLikeLog = new CharacterLikeLog();
        characterLikeLog.setUid(uid);
        characterLikeLog.setCharacterId(cid);
        characterLikeLog.setStatus(0);
        characterLikeLogRepository.save(characterLikeLog);
    }

    @Transactional
    public void characterLikeCancel(String uid, int cid){
        CharacterLikeLog characterLikeLog = characterLikeLogRepository.findByCharacterIdAndUid(cid,uid)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.(좋아요)");
                });
        characterLikeLog.setStatus(2);
        characterLikeLogRepository.save(characterLikeLog);
    }

}
