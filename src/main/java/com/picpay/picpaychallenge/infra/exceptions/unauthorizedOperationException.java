package com.picpay.picpaychallenge.infra.exceptions;

public class unauthorizedOperationException extends RuntimeException {
    public unauthorizedOperationException(String message){
        super(message);
    }
}
