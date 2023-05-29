package com.AtosExam.questions.answers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AnswerRepo extends MongoRepository<Answers,String> {
    List<Answers> findByQuestionId(String questionId);

    Map<String, List<Answers>> getAnswersGroupedByQuestionId();

}
