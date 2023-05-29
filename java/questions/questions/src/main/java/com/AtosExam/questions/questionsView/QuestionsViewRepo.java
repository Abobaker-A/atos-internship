package com.AtosExam.questions.questionsView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsViewRepo extends MongoRepository<QuestionsView,String> {
    Page<QuestionsView> findByLevelIdAndCategoryId(Integer levelId, Integer categoryId, Pageable pageable);

    Page<QuestionsView> findByLevelId(Integer levelId, Pageable pageable);

    Page<QuestionsView> findByCategoryId(Integer categoryId, Pageable pageable);
}
