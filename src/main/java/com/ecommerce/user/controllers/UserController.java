package com.ecommerce.user.controllers;

import com.ecommerce.user.dto.ClientCreateDto;
import com.ecommerce.user.dto.ClientResponseDto;
import com.ecommerce.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ClientResponseDto> createUser(@RequestBody ClientCreateDto user){
        ClientResponseDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<ClientResponseDto> loginUser(@RequestBody ClientCreateDto user, HttpServletRequest request){
        ClientResponseDto savedUser = userService.getUser(user);

        HttpSession session = request.getSession(true);

        session.setAttribute("USER_ID", savedUser.getId());
        session.setAttribute("LOGIN_TIMESTAMP", Instant.now().toEpochMilli());

        session.setMaxInactiveInterval(1 * 60); // 30 minutes TTL

        return new ResponseEntity<>(savedUser, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<ClientResponseDto> updateUser(@RequestBody ClientResponseDto user){
        ClientResponseDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllUser(){
        List<ClientResponseDto> savedUsers = userService.getAllUser();
        return new ResponseEntity<>(savedUsers, HttpStatus.OK);
    }

}
