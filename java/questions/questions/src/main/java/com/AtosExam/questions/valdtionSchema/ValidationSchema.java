//package com.AtosExam.questions.valdtionSchema;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.model.CreateCollectionOptions;
//import com.mongodb.client.model.ValidationOptions;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//public class ValidationSchema {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public void createValidationSchema() {
//        mongoTemplate.getDb().createCollection("questions",
//                new CreateCollectionOptions().validationOptions(
//                        new ValidationOptions().validator(
//                                Document.parse("    $jsonSchema: {\n" +
//                                        "      bsonType: \"object\",\n" +
//                                        "      required: [ \"name\",\"level_id\",\"category_id\",\"mark\",\"expected_time\",\"correct_answer\",\"created_by\",\"created_at\" ],\n" +
//                                        "      properties: {\n" +
//                                        "        name: {\n" +
//                                        "          bsonType: \"string\",\n" +
//                                        "          description: \"must be a characters and is required\"\n" +
//                                        "        },\n" +
//                                        "        level_id: {\n" +
//                                        "                                 bsonType: \"int\",\n" +
//                                        "                                 minimum: 1,\n" +
//                                        "                                 maximum: 4,\n" +
//                                        "                                 description: \"'level_id' must be a number in [ 1, 4 ] and is required\"\n" +
//                                        "                              },\n" +
//                                        "        category_id: {\n" +
//                                        "                                         bsonType: \"int\",\n" +
//                                        "                                         minimum: 1,\n" +
//                                        "                                         maximum: 4,\n" +
//                                        "                                         description: \"'category_id' must be a number in [ 1, 4 ] and is required\"\n" +
//                                        "                                      },\n" +
//                                        "        mark: {\n" +
//                                        "                                         bsonType: \"int\",\n" +
//                                        "                                         minimum: 1,\n" +
//                                        "                                         maximum: 5,\n" +
//                                        "                                         description: \"'mark' must be a number in [ 1, 5 ] and is required\"\n" +
//                                        "                                      },\n" +
//                                        "        expected_time: {\n" +
//                                        "                                                 bsonType: \"int\",\n" +
//                                        "                                                 minimum: 30,\n" +
//                                        "                                                 maximum: 120,\n" +
//                                        "                                                 description: \"'expected_time' must be a number in [ 30, 120 ] and is required\"\n" +
//                                        "                                              },\n" +
//                                        "\n" +
//                                        "        created_by: {\n" +
//                                        "                          bsonType: \"string\",\n" +
//                                        "                          description: \"must be a characters and is required\"\n" +
//                                        "                        },\n" +
//                                        "        correct_answer: {\n" +
//                                        "                                  bsonType: \"object\",\n" +
//                                        "                                   properties: {\n" +
//                                        "                                          name: {\n" +
//                                        "                                            bsonType: \"string\",\n" +
//                                        "                                            description: \"must be a characters and is required\"\n" +
//                                        "                                          }\n" +
//                                        "                                          }\n" +
//                                        "                                   required: [ \"name\"],\n" +
//                                        "\n" +
//                                        "                                },\n" +
//                                        "\n" +
//                                        "        \n" +
//                                        "      }\n" +
//                                        "    }\n" +
//                                        "\n")
//                        )
//                )
//        );
//    }
//
//}
