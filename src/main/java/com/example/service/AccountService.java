package com.example.service;

import com.example.auth.AuthRequest;
import com.example.auth.AuthResponse;
import com.example.exception.EmailValidationException;
import com.example.exception.UserDisableException;
import com.example.exception.UserNotFoundException;
import com.example.jwt.JwtService;
import com.example.model.dto.AccountDTO;
import com.example.model.email.EmailService;
import com.example.model.email.EmailValidator;
import com.example.model.entity.Account;
import com.example.model.mapper.AccountMapper;
import com.example.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final AccountDetailsService accountDetailsService;
    private final EmailService emailService;
    private final EmailValidator emailValidator;

    public String registerUser(AccountDTO accountDTO) {

        if (!emailValidator.test(accountDTO.email())) {
            log.info("Email ain't fit to email pattern!");
            throw new EmailValidationException("Correct the email pattern!");
        }

        Account account = accountMapper.accountDtotoAccount(accountDTO);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(true);
        account.setLocked(false);
        accountRepository.save(account);
        emailService.send(accountDTO.email(), "Registration verification", "Please read the otp code");

        return "Account created, please enable it with token!";
    }

    public AuthResponse generateToken(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        if (authentication.isAuthenticated()) {
            Account account = accountDetailsService.loadUserByUsername(request.email());
            account.setEnabled(true);
            var jwtToken = jwtService.generateToken(account);
            return new AuthResponse(jwtToken);
        }
        log.info("invalid username " + request.email());
        throw new UsernameNotFoundException("invalid username {} " + request.email());
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::accounttoAccountDto).toList();
    }

    public AccountDTO getAccountById(Long id) {
        Account account = findById(id);
        return accountMapper.accounttoAccountDto(account);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {

        Account account = findById(id);
        if (!account.isEnabled()) {
            throw new UserDisableException("Account is disable!");
        }
        account.setRoles(accountDTO.roles());
        account.setPassword(accountDTO.password());
        account.setEmail(accountDTO.email());
        account.setUsername(accountDTO.username());
        accountRepository.save(account);
        return accountMapper.accounttoAccountDto(account);
    }

    public AccountDTO deactivateAccount(Long id) {
        Account account = findById(id);
        account.setEnabled(false);
        accountRepository.save(account);
        return accountMapper.accounttoAccountDto(account);
    }

    public AccountDTO activateAccount(Long id) {
        Account account = findById(id);
        account.setEnabled(true);
        accountRepository.save(account);
        return accountMapper.accounttoAccountDto(account);
    }

    private Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Account couldn't be found!"));
    }

//    private Long getPrincipal() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Account account = (Account) authentication.getPrincipal();
//        if(account.getRoles().stream().anyMatch(s->s.equals(Role.ADMIN))){
//            return 0L;
//        }
//        else{
//            return account.getId();
//        }
//    }


}
