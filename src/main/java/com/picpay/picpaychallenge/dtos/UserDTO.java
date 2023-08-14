package com.picpay.picpaychallenge.dtos;

import com.picpay.picpaychallenge.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record UserDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String fullName,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        String password,
       @NotNull
        UserType userType,
        @NotNull
        BigDecimal balance
) {
}
