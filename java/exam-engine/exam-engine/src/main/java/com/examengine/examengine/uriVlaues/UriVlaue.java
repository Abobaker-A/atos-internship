package com.examengine.examengine.uriVlaues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UriVlaue {
    @Value("${webclient.users.base-url}")
    private String usersBaseUrl;
    @Value("${app.url}")
    private String examEngineBaseUrl;
    @Value("${webclient.questions.base-url}")
    private String qestionsBaseUrl;
    @Value("${webclient.users.byEmail-endpoint}")
    private String UserByEmailEndPoint;
    @Value("${webclient.questions.questions-endpoint}")
    private String QuestionsViewEndPoint;
    @Value("${webclient.frontEnd.base-url}")
    private String frontEndBaseUrl;
    @Value("${webclient.frontEnd.take-exam}")
    private String takeExamEndPoint;



}
