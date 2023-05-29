package com.AtosExam.questions.answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/answers")
public class AnswersController {
    @Autowired
    private AnswersService answersService;

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteAnswer(@PathVariable String id) {
        answersService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = {"/addanswer"})
    public ResponseEntity<Answers> addQuestions(@RequestBody Answers answers) {
        Answers result = answersService.addAnswer(answers);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
