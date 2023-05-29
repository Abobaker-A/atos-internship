package com.examengine.examengine.examInstance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamInstanceRepository extends MongoRepository<ExamInstance,String> {
}
