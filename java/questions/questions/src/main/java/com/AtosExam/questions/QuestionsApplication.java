package com.AtosExam.questions;
import com.AtosExam.questions.mongoConfig.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(MongoConfig.class)
public class QuestionsApplication {


	public static void main(String[] args) {

		SpringApplication.run(QuestionsApplication.class, args);
	}
}
