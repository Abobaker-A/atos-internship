package com.AtosExam.questions.questionsView;

import com.AtosExam.questions.questionController.QuestionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsViewServices {
    @Autowired
    private QuestionsViewRepo questionsViewRepo;
    @Autowired QuestionDtoMapper questionDtoMapper;


    public Optional<QuestionsView> getQuestionById(String id) {
        Optional<QuestionsView> questionsView = questionsViewRepo.findById(id);
        if (questionsView.isPresent()) {
            return questionsView;
        }
        return null;
    }
    public Page<QuestionsView> filterByLevelIdAndCategoryId(Integer levelId, Integer categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (levelId != null && categoryId != null) {
            return questionsViewRepo.findByLevelIdAndCategoryId(levelId, categoryId, pageable);
        } else if (levelId != null) {
            return questionsViewRepo.findByLevelId(levelId, pageable);
        } else if (categoryId != null) {
            return questionsViewRepo.findByCategoryId(categoryId, pageable);
        } else {
            return questionsViewRepo.findAll(pageable);
        }
    }

    public List<QuestionsView> getQuestionsByIds(List<String> ids) {
        List<QuestionsView> questionsViews = questionsViewRepo.findAllById(ids);
        return questionsViews;
    }
}
