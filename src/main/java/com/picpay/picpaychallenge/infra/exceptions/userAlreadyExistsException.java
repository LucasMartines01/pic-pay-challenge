package com.picpay.picpaychallenge.infra.exceptions;

public class userAlreadyExistsException extends RuntimeException {
    public userAlreadyExistsException(String message){
        super(message);
    }
}
