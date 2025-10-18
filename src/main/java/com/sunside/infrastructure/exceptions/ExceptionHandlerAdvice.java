package com.sunside.infrastructure.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handlerBusinessException(BusinessException ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.valueOf(422);
        ErrorResponseException errorResponseException = new ErrorResponseException(
                httpStatus.value(), httpStatus.getReasonPhrase(), List.of(ex.getMessage()));

        return handleExceptionInternal(ex, errorResponseException, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handlerInternalServerError(RuntimeException exception, WebRequest request){
        HttpStatus httpStatus = HttpStatus.valueOf(500);
        ErrorResponseException errorResponseException = new ErrorResponseException(
                httpStatus.value(), httpStatus.getReasonPhrase(), List.of(exception.getMessage()));

        return handleExceptionInternal(exception, errorResponseException, new HttpHeaders(), httpStatus, request);
    }


}
