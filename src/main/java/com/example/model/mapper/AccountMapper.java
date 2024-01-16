package com.example.model.mapper;

import com.example.model.dto.AccountDTO;
import com.example.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", source = "roles")
    Account accountDtotoAccount(AccountDTO accountDTO);
    
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", source = "roles")
    AccountDTO accounttoAccountDto(Account account);




}
