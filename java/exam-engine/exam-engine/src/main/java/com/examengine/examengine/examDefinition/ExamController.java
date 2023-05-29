package com.examengine.examengine.examDefinition;

import com.examengine.examengine.examInstance.ExamInstanceDto;
import com.examengine.examengine.examInstance.ExamInstanceService;
import com.examengine.examengine.questiondetails.QuestionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "exams")
public class ExamController {
    @Autowired
    private  ExamDefinitionService examDefinitionService;
    @Autowired
    private ExamInstanceService examInstanceService;

    // Create Exam Definition API (SME)
    @PostMapping("/definitions")
    public ResponseEntity<ExamDefinitionDto> createExamDefinition(@RequestBody ExamDefinitionDto examDefinitionDto) {
        ExamDefinitionDto createdExamDefinition = examDefinitionService.createExamDefinition(examDefinitionDto);
        return new ResponseEntity<>(createdExamDefinition, HttpStatus.CREATED);
    }
    @GetMapping("/definitions")
    public ResponseEntity<List<ExamDefinitionDto>> getExamDefinition() {
        List<ExamDefinitionDto> examDefinitionDtos = examDefinitionService.getExamDefinition();
        return new ResponseEntity<>(examDefinitionDtos, HttpStatus.OK);
    }
    @DeleteMapping("/definitions/{id}")
    public ResponseEntity<Void> getExamDefinition(@PathVariable String id) {
         examDefinitionService.deleteExamDefinition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Assign Exam to Student API (SME)
    @PostMapping("/assignments")
    public ResponseEntity<Void> assignExamToStudent(@RequestBody AssignExamToStudentDto assignExamToStudentDto) throws JsonProcessingException {
        examInstanceService.assignExamToStudent(assignExamToStudentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Take Exam API (Student)

    @GetMapping("/instances/{instanceId}")
    public ResponseEntity<ExamInstanceDto> takeExam(@PathVariable String instanceId) {
        ExamInstanceDto examInstanceDto = examInstanceService.takeExam(instanceId);
        return new ResponseEntity<>(examInstanceDto, HttpStatus.OK);
    }
    @GetMapping("/instances")
    public ResponseEntity<List<ExamInstanceDto>> getAllExaminstances() {
        List<ExamInstanceDto> examInstanceDto = examInstanceService.getAllExamInstances();
        return new ResponseEntity<>(examInstanceDto, HttpStatus.OK);
    }


    // Submit Exam API (Student)
    @PostMapping("/instances/{instanceId}/submission")
    public ResponseEntity<ExamInstanceDto> submitExam(@PathVariable String instanceId, @RequestBody List<QuestionDto> questionsDto) throws JsonProcessingException {
        ExamInstanceDto examInstanceDto= examInstanceService.submitExam(instanceId, questionsDto);
        return new ResponseEntity<>(examInstanceDto,HttpStatus.OK);
    }



}
