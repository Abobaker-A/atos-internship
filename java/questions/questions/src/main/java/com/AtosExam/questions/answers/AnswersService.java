package com.AtosExam.questions.answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class AnswersService {
    @Autowired
    AnswerRepo answerRepo ;
   public List<Answers> addAnswers(List<Answers> answers){
       return answerRepo.insert(answers);
   }


    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteAnswersByQuestionId(String questionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("question_id").is(questionId));
        mongoTemplate.remove(query, "answers");
    }





    public void deleteAnswer(String id) {
        answerRepo.deleteById(id);
    }
    public Answers addAnswer(Answers answers){
       return answerRepo.insert(answers);
    }
}
