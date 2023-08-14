package com.picpay.picpaychallenge.controller;

import com.picpay.picpaychallenge.dtos.TransactionDTO;
import com.picpay.picpaychallenge.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionDTO transactionDTO){
        return transactionService.createTransaction(transactionDTO);
    }
}
