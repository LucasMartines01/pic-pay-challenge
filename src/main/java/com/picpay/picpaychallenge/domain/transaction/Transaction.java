package com.picpay.picpaychallenge.domain.transaction;

import com.picpay.picpaychallenge.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal transactionAmount;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;
    private LocalDateTime timeStamp;

    public Transaction(BigDecimal value, User payer, User payee, LocalDateTime timeStamp){
        this.transactionAmount = value;
        this.payee = payee;
        this.payer = payer;
        this.timeStamp = timeStamp;
    }
}
