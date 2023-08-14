package com.picpay.picpaychallenge.services;
import com.picpay.picpaychallenge.domain.transaction.Transaction;
import com.picpay.picpaychallenge.domain.user.User;
import com.picpay.picpaychallenge.domain.user.UserType;
import com.picpay.picpaychallenge.dtos.TransactionDTO;
import com.picpay.picpaychallenge.infra.exceptions.InsufficientBallanceException;
import com.picpay.picpaychallenge.infra.exceptions.unauthorizedOperationException;
import com.picpay.picpaychallenge.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity createTransaction(TransactionDTO transactionDTO) {
        var payee = userService.findById(transactionDTO.payeeID());
        var payer = userService.findById(transactionDTO.payerId());

        checkTransaction(payer, transactionDTO.value());

       var transaction =  new Transaction(transactionDTO.value(), payer, payee, LocalDateTime.now());

        transactionRepository.save(transaction);
        payer.setBalance(payer.getBalance().subtract(transactionDTO.value()));
        payee.setBalance(payee.getBalance().add(transactionDTO.value()));

        userService.updateUsers(payer, payee);

        return new ResponseEntity(transactionDTO, HttpStatus.CREATED);
    }

    private void checkTransaction(User payer, BigDecimal transactionValue) {
        if (payer.getBalance().compareTo(transactionValue) < 0) {
            throw new InsufficientBallanceException("Saldo insuficiente para realizar essa operação.");
        }

        if (payer.getUserType() == UserType.MERCHANT) {
            throw new unauthorizedOperationException("Lojistas não podem realizar transferências");
        }

        if(!authorizeTransaction()){
            throw new unauthorizedOperationException("Transação não autorizada.");
        }
    }

    public boolean authorizeTransaction() {
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                Map.class);

        String message = (String) response.getBody().get("message");

        return response.getStatusCode() == HttpStatus.OK && message.equalsIgnoreCase("Autorizado");
    }


}
