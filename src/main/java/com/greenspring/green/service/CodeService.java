package com.greenspring.green.service;

import com.greenspring.green.model.CharacterBoard;
import com.greenspring.green.model.CodeTable;
import com.greenspring.green.model.TwtUser;
import com.greenspring.green.repo.CharacterBoardRepository;
import com.greenspring.green.repo.CodeTableRepository;
import com.greenspring.green.repo.TwtUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

//Spring do component scan register to Bean, IoC
@Service
public class CodeService {

    private final CodeTableRepository codeTableRepository;

    public CodeService(CodeTableRepository codeTableRepository) {
        this.codeTableRepository = codeTableRepository;
    }

    @Transactional
    public String getLocalizedString(String code,String lang){
        CodeTable codeTable = codeTableRepository.findByCodeNameEquals(code).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });
        if(lang.equals("EN")){
            return codeTable.getEN();
        }
        if(lang.equals("JP")){
            return codeTable.getJP();
        }
        return codeTable.getKR();
    }


}
