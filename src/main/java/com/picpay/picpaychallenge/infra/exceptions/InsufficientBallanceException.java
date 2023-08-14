package com.picpay.picpaychallenge.infra.exceptions;

public class InsufficientBallanceException extends RuntimeException{
        public InsufficientBallanceException(String message){
            super(message);
        }


}
