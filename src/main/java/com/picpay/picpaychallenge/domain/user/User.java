package com.picpay.picpaychallenge.domain.user;

import com.picpay.picpaychallenge.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String fullName;
    private String cpf;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private BigDecimal balance;

    public User(UserDTO userDTO){
        this.email = userDTO.email();
        this.fullName = userDTO.fullName();
        this.cpf = userDTO.cpf();
        this.password = userDTO.password();
        this.userType = userDTO.userType();
        this.balance = userDTO.balance();
    }
}
