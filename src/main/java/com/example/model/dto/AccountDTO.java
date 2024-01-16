package com.example.model.dto;

import com.example.model.entity.Role;

import java.util.Set;

public record AccountDTO(

        String username,
        String email,
        String password,
        Set<Role> roles


) {

}
