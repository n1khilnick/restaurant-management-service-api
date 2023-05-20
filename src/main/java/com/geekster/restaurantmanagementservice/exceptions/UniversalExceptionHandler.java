package com.geekster.restaurantmanagementservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UniversalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleUniversalException(Exception ex) {
        Map<String, String> errorObj = new HashMap<>();

        String error = "error";
        String httpErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR.toString().replace("_", " ");

        errorObj.put(error, httpErrorStatus);

        String exception = "exception";
        String exceptionName = ex.getClass().getSimpleName();

        errorObj.put(exception, exceptionName);

        String message = "message";
        String localizedMessage = ex.getLocalizedMessage();

        errorObj.put(message, localizedMessage);


//        String path = "validationError";
//        String pathName = ex.getConstraintViolations().
//        errorObj.put(path,pathName);


        return new ResponseEntity<Map<String, String>>(errorObj,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<List<Map<String,String>>> handleIllegalStateException(IllegalStateException ex){
//        List< Map<String,String>> resp = new ArrayList<>();
//
//        Map<String,String> exceptionMap = new HashMap<>();
//        String error = "error";
//        String  exceptionName = ex.getClass().toString();
//
//        exceptionMap.put(error,exceptionName);
//
//        String message = "message";
//        String  localizedMessage = ex.getLocalizedMessage();
//
//        exceptionMap.put(message,localizedMessage);
//
//        resp.add(exceptionMap);
//
//       return new ResponseEntity<List<Map<String,String>>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

