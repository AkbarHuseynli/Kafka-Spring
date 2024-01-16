package com.example.controller;

import com.example.model.dto.AccountDTO;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.updateAccount(id, accountDTO));
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<AccountDTO> deactivateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deactivateAccount(id));
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<AccountDTO> activateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.activateAccount(id));
    }



}
