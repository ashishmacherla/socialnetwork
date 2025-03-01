package com.ashish.project.socialnetwork.exception.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FriendshipException extends RuntimeException{
    String message;
    String code;
    public FriendshipException(Exception exception) {
        super(exception);
    }

    public FriendshipException(String message,String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
