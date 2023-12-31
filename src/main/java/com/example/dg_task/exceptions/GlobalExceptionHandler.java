package com.example.dg_task.exceptions;


import com.example.dg_task.utilis.AppResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        //عشان يعمل اكسبشن علي ال validation

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<String>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField()+" "+ error.getDefaultMessage());
        }

        for(ObjectError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getObjectName()+ " "+ error.getDefaultMessage());
        }

        return AppResponse.generateResponse("please validate the following fields", HttpStatus.BAD_REQUEST, errors, false);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Object> handleUnauthorizedException(BadCredentialsException ex) {

        return AppResponse.generateResponse("Full authentication is required to access this resource", HttpStatus.UNAUTHORIZED, null, false) ;// ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {

        return AppResponse.generateResponse("Invalid Username Or Password", HttpStatus.UNAUTHORIZED, null, false) ;// ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Object> handleInternalServerErrorException(BadCredentialsException ex) {

        return AppResponse.generateResponse("Internal Server Error", HttpStatus.UNAUTHORIZED, null, false) ;// ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
    }



    //add all your exceptions here

    @ExceptionHandler(InvalidValuesException.class)
    public ResponseEntity<?> handleInvalidValuesException(InvalidValuesException ex) {
        return  AppResponse.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null, false);

    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> handleDuplicateRecordException(DuplicateRecordException ex) {


        return  AppResponse.generateResponse(ex.getMessage(), HttpStatus.OK, null, false);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException ex) {


        return  AppResponse.generateResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null, true);
    }
}
