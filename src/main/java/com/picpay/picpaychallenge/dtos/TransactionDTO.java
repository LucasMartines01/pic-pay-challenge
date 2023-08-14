package com.picpay.picpaychallenge.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(@NotBlank String payerId, @NotBlank String payeeID, @NotNull BigDecimal value) {
}
