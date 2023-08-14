package com.picpay.picpaychallenge.infra.exceptions;

public class userNotFoundException extends RuntimeException {

    public userNotFoundException(String message){
        super(message);
    }
}
