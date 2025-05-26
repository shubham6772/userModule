package com.ecommerce.user.controllers;

import com.ecommerce.user.dto.ClientDto;
import com.ecommerce.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<ClientDto> createUser(@RequestBody ClientDto user){
          System.out.println(user);
          ClientDto savedUser = userService.createUser(user);
          return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateUser(@RequestBody ClientDto user){
        ClientDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllUser(){
        List<ClientDto> savedUsers = userService.getAllUser();
        return new ResponseEntity<>(savedUsers, HttpStatus.OK);
    }
}
