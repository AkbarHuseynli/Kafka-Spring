package com.example.service;

import com.example.model.dto.AccountDTO;
import com.example.model.mapper.AccountMapper;
import com.example.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mock.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private AccountDTO accountDTO;
    private AccountMapper accountMapper;
    private AccountService accountService;
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp(){
        accountRepository = Mockito.mock(AccountRepository.class);

    }

    @AfterEach
    public void tearDown(){

    }
}