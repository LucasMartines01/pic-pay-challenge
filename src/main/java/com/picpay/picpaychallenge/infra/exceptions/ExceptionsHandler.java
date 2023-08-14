package com.picpay.picpaychallenge.infra.exceptions;

import com.picpay.picpaychallenge.dtos.ExceptionsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(userAlreadyExistsException.class)
    public ResponseEntity userAlreadyExists(userAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionsDTO(exception.getMessage()));
    }
    @ExceptionHandler(userNotFoundException.class)
    public ResponseEntity userNotFound(userNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionsDTO(exception.getMessage()));
    }

    @ExceptionHandler(InsufficientBallanceException.class)
    public ResponseEntity insufficienteBallance(InsufficientBallanceException exception) {
        return ResponseEntity.badRequest().body(new ExceptionsDTO(exception.getMessage()));
    }

    @ExceptionHandler(unauthorizedOperationException.class)
    public ResponseEntity unauthorizedOperation(unauthorizedOperationException exception) {
        ExceptionsDTO exceptionDTO = new ExceptionsDTO(exception.getMessage());
        return new ResponseEntity(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }






    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(validationErrors::new).toList());
    }


    private record validationErrors(String campo, String mensagem) {
        public validationErrors(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
