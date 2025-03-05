package com.sunside.service;

import com.sunside.dto.user.LoginDTORequest;
import com.sunside.dto.user.UserDTORequest;
import com.sunside.dto.user.UserDTOResponse;
import com.sunside.exceptions.BusinessException;
import com.sunside.mapper.UserMapper;
import com.sunside.model.User;
import com.sunside.repository.UserRepository;
import com.sunside.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTOResponse> findAll(){
        return userRepository.findAll().stream().map(UserMapper::map).toList();
    }

    public UserDTOResponse create(UserDTORequest userDTO) {
        User user = UserMapper.map(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.map(userRepository.save(user));
    }

    public void deleteById(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public String login(LoginDTORequest login){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);

    }

    public UserDTOResponse findByUsername(String username) {
        return UserMapper.map(userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Usuário inexistente")));
    }
}
