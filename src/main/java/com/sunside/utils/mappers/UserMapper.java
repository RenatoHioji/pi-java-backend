package com.sunside.utils.mappers;

import com.sunside.adapters.inbound.dto.UserDTORequest;
import com.sunside.adapters.outbound.dto.UserDTOResponse;
import com.sunside.domain.User;

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
