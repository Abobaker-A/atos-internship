package com.examengine.examengine.client;

import com.examengine.examengine.uriVlaues.UriVlaue;
import com.examengine.examengine.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserApis {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private UriVlaue uriVlaue;
    public User getUserByEmail(String userEmail){
        return  webClientBuilder.build()
                .get()
                .uri( uriVlaue.getUsersBaseUrl()+uriVlaue.getUserByEmailEndPoint()+ userEmail)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
}
