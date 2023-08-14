package com.picpay.picpaychallenge.services;

import com.picpay.picpaychallenge.domain.user.User;
import com.picpay.picpaychallenge.dtos.UserDTO;
import com.picpay.picpaychallenge.infra.exceptions.userAlreadyExistsException;
import com.picpay.picpaychallenge.infra.exceptions.userNotFoundException;
import com.picpay.picpaychallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public ResponseEntity<UserDTO> saveUser(UserDTO user){
        boolean userAlreadyExists = checkIfEmailOrCpfAlreadyExists(user.cpf(), user.email());
        if(!userAlreadyExists){
            throw new userAlreadyExistsException("Usuário já cadastrado.");
        }

        User newUser = new User(user);

        repository.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public List<User> getAllUsers(){
       return repository.findAll();
    }

    public boolean checkIfEmailOrCpfAlreadyExists(String cpf, String email){
        return repository.findUserByCpf(cpf).isEmpty() && repository.findUserByEmail(email).isEmpty();
    }

    public void updateUsers(List<User> users){


        repository.saveAll(users);
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() ->  new userNotFoundException("Usuário não encontrado"));
    }
}
