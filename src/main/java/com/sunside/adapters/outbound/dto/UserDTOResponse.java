package com.sunside.adapters.outbound.dto;

import com.sunside.domain.User;

import java.util.UUID;

public record UserDTOResponse (
        UUID id,
        String username
){
    public UserDTOResponse(User user){
        this(user.getId(), user.getUsername());
    }
}
