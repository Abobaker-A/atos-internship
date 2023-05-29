package com.AtosExam.questions.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValodtionError {
private List<String> errors;
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy hh:mm:ss")
private Date timestamp;

public void addError(String err){
    this.errors.add(err);
}

    public ValodtionError() {
    this.timestamp = new Date();
    this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
