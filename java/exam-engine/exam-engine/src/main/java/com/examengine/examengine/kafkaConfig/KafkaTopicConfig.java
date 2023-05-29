package com.examengine.examengine.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic assignedExamTopic(){

        return TopicBuilder.name("assignedExam")
                .partitions(2)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic examGradingTopic(){

        return TopicBuilder.name("examGrading")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic examSubmissionTopic(){

        return TopicBuilder.name("examSubmission")
                .partitions(2)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic examGradingToUserTopic(){

        return TopicBuilder.name("examGradingToUser")
                .partitions(2)
                .replicas(1)
                .build();
    }


}

