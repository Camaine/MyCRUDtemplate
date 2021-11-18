package com.greenspring.green.service;

import com.greenspring.green.model.RoleType;
import com.greenspring.green.model.TwitterToken;
import com.greenspring.green.model.TwtUser;
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
        twtUser.setRole(RoleType.USER);
        twtUserRepository.save(twtUser);
    }


}
