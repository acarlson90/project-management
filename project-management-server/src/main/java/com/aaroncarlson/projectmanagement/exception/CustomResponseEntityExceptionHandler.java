package com.aaroncarlson.projectmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * @ControllerAdvice - Global exception which applies to all Controllers (classes marked with the @Controller or
 * @RestController annotations)
 * @ExceptionHandler - Designate a method to handle exceptions
 * Using @ControllerAdvice along with @ExceptionHandler prevents the need to create a base controller with exception logic
 * and having all other controllers to extend the base controller by providing global (and more specific) error handling
 * so the developer does not need to remember to implement them or extend a base call every time.
 */
@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleResponseEntityException(CustomException exception, WebRequest request) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(exception.getMessage());

        return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

}
