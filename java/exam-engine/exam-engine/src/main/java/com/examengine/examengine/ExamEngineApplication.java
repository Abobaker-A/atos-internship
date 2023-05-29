package com.examengine.examengine;

import com.examengine.examengine.mongoConfig.MongoConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import(MongoConfig.class)
public class ExamEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamEngineApplication.class, args);
	}



}
