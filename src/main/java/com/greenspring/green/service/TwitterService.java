package com.greenspring.green.service;

import com.greenspring.green.model.TwitterToken;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.RequestToken;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Service
public class TwitterService {

    @Autowired
    private TwitterToken twitterToken;

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


}
