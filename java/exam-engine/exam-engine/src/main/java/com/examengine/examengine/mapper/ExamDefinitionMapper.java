package com.examengine.examengine.mapper;

import com.examengine.examengine.examDefinition.ExamDefinition;
import com.examengine.examengine.examDefinition.ExamDefinitionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamDefinitionMapper {
    public ExamDefinition fromExamDefinitionDtoToEntity(ExamDefinitionDto examDefinitionDto) {
        ExamDefinition examDefinition = new ExamDefinition();
        examDefinition.setId(examDefinitionDto.getId());
        examDefinition.setDuration(examDefinitionDto.getDuration());
        examDefinition.setName(examDefinitionDto.getName());
        examDefinition.setPassingScore(examDefinitionDto.getPassingScore());
        examDefinition.setQuestionIds(examDefinitionDto.getQuestionIds());
        return examDefinition;
    }

    public ExamDefinitionDto fromExamDefinitionToDto(ExamDefinition examDefinition) {
        ExamDefinitionDto examDefinitionDto = new ExamDefinitionDto();
        examDefinitionDto.setId(examDefinition.getId());
        examDefinitionDto.setName(examDefinition.getName());
        examDefinitionDto.setDuration(examDefinition.getDuration());
        examDefinitionDto.setPassingScore(examDefinition.getPassingScore());
        examDefinitionDto.setQuestionIds(examDefinition.getQuestionIds());
        return examDefinitionDto;
    }
    public List<ExamDefinitionDto> fromExamDefinitionToDtoList(List<ExamDefinition> examDefinitions){
        List<ExamDefinitionDto> examDefinitionDtos =new ArrayList<>();
        for(ExamDefinition examDefinition:examDefinitions){
            examDefinitionDtos.add(fromExamDefinitionToDto(examDefinition));
        }
        return examDefinitionDtos;
    }






}
