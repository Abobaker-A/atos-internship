package com.AtosExam.questions.questionController;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionsRepo extends MongoRepository<Questions,String>  {
    Questions findByName(String name);



}
