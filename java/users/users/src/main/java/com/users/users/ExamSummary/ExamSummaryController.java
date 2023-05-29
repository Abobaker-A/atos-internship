package com.users.users.ExamSummary;
import com.users.users.users.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examsummary")
public class ExamSummaryController {
    @Autowired
    private ExamSummaryService examSummaryService;


    @GetMapping(value = "{userId}")
    public ResponseEntity<Page<ExamSummaryDTO>> getExamSummariesByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ExamSummaryDTO> examSummaries = examSummaryService.getExamSummariesByUserId(userId, page, size);
        return ResponseEntity.ok(examSummaries);
    }
    @GetMapping("/by-id/{examInstanceId}")
    public ResponseEntity<ExamSummaryDTO> getExamSummaryByExamInstanceId(@PathVariable String examInstanceId) {
        ExamSummaryDTO examSummaryDTO = examSummaryService.getExamSummaryByExamInstanceId(examInstanceId);
        return ResponseEntity.ok(examSummaryDTO);
    }

}
