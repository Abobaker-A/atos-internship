package com.examengine.examengine.client;

import com.examengine.examengine.examDefinition.ExamDefinition;
import com.examengine.examengine.questiondetails.QuestionsView;
import com.examengine.examengine.uriVlaues.UriVlaue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class QestionBankApis {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private UriVlaue uriVlaue;
    public QuestionsView getQuestionById(String id){
        return  webClientBuilder.build()
                .get()
                .uri( uriVlaue.getQestionsBaseUrl()+"/questions/"+ id)
                .retrieve()
                .bodyToMono(QuestionsView.class)
                .block();
    }
    public List<QuestionsView> getQuestionsView(ExamDefinition examDefinition){
        return   webClientBuilder.build()
                .post()
                .uri(uriVlaue.getQestionsBaseUrl()+uriVlaue.getQuestionsViewEndPoint())
                .bodyValue(examDefinition.getQuestionIds())
                .retrieve()
                .bodyToFlux(QuestionsView.class)
                .collectList()
                .block();
    }
}
