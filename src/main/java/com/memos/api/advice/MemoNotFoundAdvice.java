package com.memos.api.advice;

import com.memos.api.exception.MemoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MemoNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MemoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String memoNotFoundHandler(MemoNotFoundException ex) {
        return ex.getMessage();
    }
}
