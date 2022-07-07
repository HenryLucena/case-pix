package com.example.demo.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InternalServerErrorException.class)
    public ResponseEntity<StandardObjectException> handleInternalServerErrorException(InternalServerErrorException e, HttpServletRequest request){
        StandardObjectException error = new StandardObjectException(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI(), new Date().getTime());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage(), errors);
        return handleExceptionInternal(e, apiError, headers, apiError.getStatus(), request);
    }

    //todo exception para JSON parse

    //TODO exception para contraint violation
}
