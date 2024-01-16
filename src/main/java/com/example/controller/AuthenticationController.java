package com.example.controller;

import com.example.auth.AuthRequest;
import com.example.auth.AuthResponse;
import com.example.model.dto.AccountDTO;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AccountService accountService;

    @PostMapping("/generateToken")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(accountService.generateToken(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(accountService.registerUser(accountDTO));
    }

}
