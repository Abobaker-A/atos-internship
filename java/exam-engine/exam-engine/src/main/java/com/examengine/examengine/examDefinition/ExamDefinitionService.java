package com.examengine.examengine.examDefinition;

import com.examengine.examengine.mapper.ExamDefinitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamDefinitionService {
    @Autowired
    private ExamDefinitionRepository examDefinitionRepository;
    @Autowired
    private ExamDefinitionMapper examDefinitionMapper;
    public ExamDefinitionDto createExamDefinition(ExamDefinitionDto examDefinitionDto) {
        ExamDefinition examDefinition = examDefinitionMapper.fromExamDefinitionDtoToEntity(examDefinitionDto);
        ExamDefinition savedExamDefinition = examDefinitionRepository.save(examDefinition);
        return examDefinitionMapper.fromExamDefinitionToDto(savedExamDefinition);
    }
    public List<ExamDefinitionDto> getExamDefinition() {
        List<ExamDefinition> examDefinitions  = examDefinitionRepository.findAll();
        return  examDefinitionMapper.fromExamDefinitionToDtoList(examDefinitions);
    }

    public void deleteExamDefinition(String id){
        examDefinitionRepository.deleteById(id);
    }
}
