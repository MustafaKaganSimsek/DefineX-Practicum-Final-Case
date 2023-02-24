package com.final_case.DefineXPracticumFinalCase.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Log4j2
@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    //data object or entity object validation method

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String,String> errors =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(errors);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CreditNotFoundExeption.class)
    public ResponseEntity<?> creditNotFoundExeption(CreditNotFoundExeption exception)throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException exception)throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsCustomerException.class)
    public ResponseEntity<?> existsCustomerException(ExistsCustomerException exception)throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistCreditException.class)
    public ResponseEntity<?> existsCustomerException(ExistCreditException exception)throws IOException {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }





}
