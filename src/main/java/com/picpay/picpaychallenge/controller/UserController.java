package com.picpay.picpaychallenge.controller;

import com.picpay.picpaychallenge.domain.user.User;
import com.picpay.picpaychallenge.dtos.UserDTO;
import com.picpay.picpaychallenge.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserDTO userDTO){
        userService.saveUser(userDTO);

        return new ResponseEntity(userDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
      List<User> users = userService.getAllUsers();


      return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
