package com.AtosExam.questions.questionController;

import com.AtosExam.questions.answers.AnswersService;
import com.AtosExam.questions.questionsView.QuestionsView;
import com.AtosExam.questions.questionsView.QuestionsViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/questions")
public class QuestionsController {
    @Autowired
    private QuestionServices questionServices ;
    @Autowired
    private AnswersService answersService ;
    @Autowired
    private QuestionsViewServices questionsViewServices;

    @GetMapping("/list/filter")
    public Page<QuestionsView> displayListQuestion(@RequestParam(required = false) Integer levelid,
                                                   @RequestParam(required = false) Integer categoryid,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "25") int size) {
        return questionsViewServices.filterByLevelIdAndCategoryId(levelid, categoryid, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<QuestionsView>> getQuestionsById(@PathVariable String id) {
        Optional<QuestionsView> questionsView = questionsViewServices.getQuestionById(id);
        return ResponseEntity.ok(questionsView);

    }
    @PostMapping("/by-ids")
    public ResponseEntity<List<QuestionsView>> getQuestionsByIds(@RequestBody List<String> ids) {
        List<QuestionsView> questionsViews = questionsViewServices.getQuestionsByIds(ids);
        return ResponseEntity.ok(questionsViews);
    }
    @PostMapping(value = {"/add"})
    public ResponseEntity<QuestionsDto> addQuestions(@RequestBody QuestionsDto questions) {
        QuestionsDto result = questionServices.addQuestions(questions);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteQuestions(@PathVariable String id) {

        questionServices.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
