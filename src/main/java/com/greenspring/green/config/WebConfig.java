package com.greenspring.green.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","http://greenworld.dscloud.me:3000","https://www.furse.io","https://furse.io","https://nextjs.d26sdrbm8fvd8f.amplifyapp.com")
                .allowedMethods("GET", "POST","PUT","DELETE");
    }
}
