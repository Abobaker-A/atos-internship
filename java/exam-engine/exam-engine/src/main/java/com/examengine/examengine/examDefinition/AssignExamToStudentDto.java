package com.examengine.examengine.examDefinition;

import com.examengine.examengine.GeneratedLink.GeneratedLinkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignExamToStudentDto {
   private String examdefinitionid;
    private String useremail;
    private  String createdby ;
    private Date scheduledTimeFrom ;
    private  Date scheduledTimeTo ;

}
