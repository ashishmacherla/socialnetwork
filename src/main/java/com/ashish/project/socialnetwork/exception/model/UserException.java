package com.ashish.project.socialnetwork.exception.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserException extends RuntimeException{
    String message;
    String code;

    public UserException(Exception exception) {
        super(exception);
    }

    public UserException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
