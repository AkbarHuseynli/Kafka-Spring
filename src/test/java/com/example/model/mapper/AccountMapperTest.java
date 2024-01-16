package com.example.model.mapper;

import com.example.model.dto.AccountDTO;
import com.example.model.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


class AccountMapperTest {
    private AccountMapper accountMapper;
    private Account account;
    private AccountDTO accountDTO;

    @BeforeEach
    void setUp() {
        accountMapper = Mockito.mock(AccountMapper.class);
    }

    @Test
    void shouldMapAccountDtoToAccount() {
    }

    @Test
    void shouldMapAccountToAccountDto() {
    }
}