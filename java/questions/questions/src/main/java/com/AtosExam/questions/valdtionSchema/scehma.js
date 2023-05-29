    $jsonSchema: {
      bsonType: "object",
      required: [ "name","level_id","category_id","mark","expected_time","correct_answer","created_by","created_at" ],
      properties: {
        name: {
          bsonType: "string",
          description: "must be a characters and is required"
        },
        level_id: {
                                 bsonType: "int",
                                 minimum: 1,
                                 maximum: 4,
                                 description: "'level_id' must be a number in [ 1, 4 ] and is required"
                              },
        category_id: {
                                         bsonType: "int",
                                         minimum: 1,
                                         maximum: 4,
                                         description: "'category_id' must be a number in [ 1, 4 ] and is required"
                                      },
        mark: {
                                         bsonType: "int",
                                         minimum: 1,
                                         maximum: 5,
                                         description: "'mark' must be a number in [ 1, 5 ] and is required"
                                      },
        expected_time: {
                                                 bsonType: "int",
                                                 minimum: 30,
                                                 maximum: 120,
                                                 description: "'expected_time' must be a number in [ 30, 120 ] and is required"
                                              },

        created_by: {
                          bsonType: "string",
                          description: "must be a characters and is required"
                        },
        correct_answer: {
                                  bsonType: "object",
                                   properties: {
                                          name: {
                                            bsonType: "string",
                                            description: "must be a characters and is required"
                                          }
                                          }
                                   required: [ "name"],

                                },


      }
    }

