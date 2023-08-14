package com.picpay.picpaychallenge.repositories;

import com.picpay.picpaychallenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findUserByCpf(String cpf);
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findById(String id);
}
