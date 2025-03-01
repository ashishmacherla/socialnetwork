package com.ashish.project.socialnetwork.exception.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ashish.project.socialnetwork.Constants.NOT_FOUND_ERROR_CODE;

@RestControllerAdvice
public class GobalExceptionalHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> UserException(UserException ex) {
        if(ex.getCode().equals(NOT_FOUND_ERROR_CODE))
            return ResponseEntity.badRequest().body(ex.getMessage()); // Returns 404 with error message
        else
            return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ExceptionHandler(FriendshipException.class)
    public ResponseEntity<String> FriendshipException(FriendshipException ex) {
        if(ex.getCode().equals(NOT_FOUND_ERROR_CODE))
            return ResponseEntity.ok().body(ex.getMessage()); // Returns 404 with error message
        else
            return ResponseEntity.internalServerError().body(ex.getMessage());
    }

}
