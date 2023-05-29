package com.AtosExam.questions.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.List;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrosMsg> handelApiExpections(ApiBaseException ex ){
        ErrosMsg details = new ErrosMsg(ex.getMessage());
        return new ResponseEntity<>(details,ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ValodtionError valodtionError = new ValodtionError();

        List<FieldError> fieldErrors =ex.getBindingResult().getFieldErrors();

        for(FieldError f:fieldErrors){
            valodtionError.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<>(valodtionError, HttpStatus.BAD_REQUEST);
    }
}
