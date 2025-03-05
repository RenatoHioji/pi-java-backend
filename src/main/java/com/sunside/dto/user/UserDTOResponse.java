package com.sunside.dto.user;

import com.sunside.model.User;

import java.util.UUID;

public record UserDTOResponse (
        UUID id,
        String username
){
    public UserDTOResponse(User user){
        this(user.getId(), user.getUsername());
    }
}
