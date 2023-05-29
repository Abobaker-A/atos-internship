package com.users.users.ExamSummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamSummaryRepository extends MongoRepository<ExamSummary,String> {
    Page<ExamSummary> findByUserId(String userId, Pageable paging);

    Optional<ExamSummary> findByExamInstanceId(String examInstanceId);
}
