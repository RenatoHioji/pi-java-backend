package com.sunside.mapper;

import com.sunside.dto.UserDTORequest;
import com.sunside.dto.UserDTOResponse;
import com.sunside.model.User;

public class UserMapper {
    public static User map(UserDTORequest userDTORequest){
        return User.builder()
                .username(userDTORequest.username())
                .password(userDTORequest.password())
                .build();
    }
    public static UserDTOResponse map(User user){
        return new UserDTOResponse(user);
    }
}
