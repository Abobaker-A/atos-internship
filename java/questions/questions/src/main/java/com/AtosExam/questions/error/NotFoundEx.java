package com.AtosExam.questions.error;

import org.springframework.http.HttpStatus;

public class NotFoundEx extends ApiBaseException {
    public NotFoundEx(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode(){
        return  HttpStatus.NOT_FOUND ;
    }
}
