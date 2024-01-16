package com.example.service;

import com.example.exception.UserNotFoundException;
import com.example.model.entity.Account;
import com.example.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public Account loadUserByUsername(String login) {

        return accountRepository.findByEmailOrUsername(login, login)
                .orElseThrow(() -> {
                    log.error(String.format("Account %s not found", login));
                    return new UserNotFoundException("Account couldn't be found");
                });

    }
}
