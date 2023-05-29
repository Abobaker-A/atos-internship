package com.examengine.examengine.examDefinition;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDefinitionRepository extends MongoRepository<ExamDefinition,String> {

}
