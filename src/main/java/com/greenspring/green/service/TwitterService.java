package com.greenspring.green.service;

import com.greenspring.green.model.*;
import com.greenspring.green.model.User;
import com.greenspring.green.repo.TwtUserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.*;
import twitter4j.auth.RequestToken;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Service
public class TwitterService {

    @Autowired
    private TwitterToken twitterToken;

    @Autowired
    private TwtUserRepository twtUserRepository;

    public void twitterLogin(){
        System.out.println("CONSUMER " + twitterToken.getKey());
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(twitterToken.getKey(), twitterToken.getSecret());

        try{
            RequestToken requestToken = twitter.getOAuthRequestToken();
        }catch (Exception e){

        }
        return;
    }


    @Transactional
    public void twtUserRegister(TwtUser twtUser){
        twtUser.setRole("USER");
        twtUser.setLang("KR");
        twtUserRepository.save(twtUser);
    }

    @Transactional(readOnly = true)
    public TwtUser twtUserInfo(String uid){
        TwtUser twtUser = twtUserRepository.findByUid(uid)
                .orElseThrow(()->{
                    return new IllegalArgumentException("Can't find user");
                });
        return twtUser;
    }

    @Transactional
    public void twtUserUpdate(String uid, TwtUser requestUser){
        TwtUser twtUser = twtUserRepository.findByUid(uid)
                .orElseThrow(()->{
                    return new IllegalArgumentException("Can't find user");
                });
        twtUser.setDisplayName(requestUser.getDisplayName());
        twtUser.setScreenName(requestUser.getScreenName());
        twtUser.setLang(requestUser.getLang());
        twtUser.setPhotoURL(requestUser.getPhotoURL());
        System.out.println("update : " + twtUser.getPhotoURL());
        //해당 함수 종료시 트랜젝션종료, 더티체킹이 이루어지면서 자동업데이트
    }


}
