package com.AtosExam.questions.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrosMsg {
    private String message ;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss ")
    private Date timestamp;

    public ErrosMsg(String message) {
        this();
        this.message = message;
    }

    public ErrosMsg() {
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
