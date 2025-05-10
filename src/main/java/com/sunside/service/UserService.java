package com.sunside.service;

import com.sunside.dto.user.LoginDTORequest;
import com.sunside.dto.user.LoginDTOResponse;
import com.sunside.dto.user.UserDTORequest;
import com.sunside.dto.user.UserDTOResponse;
import com.sunside.exceptions.BusinessException;
import com.sunside.mapper.UserMapper;
import com.sunside.model.User;
import com.sunside.repository.UserRepository;
import com.sunside.security.JwtTokenProvider;
import com.sunside.utils.IdUtills;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final String TOKEN_TYPE = "Bearer";

    public List<UserDTOResponse> findAll(){
        return userRepository.findAll().stream().map(UserMapper::map).toList();
    }

    public LoginDTOResponse create(UserDTORequest userDTO) {
        if(userRepository.findByUsername(userDTO.username()).isPresent()){
            throw new BusinessException("Usuário existente");
        }
        User user = UserMapper.map(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginDTOResponse(jwtTokenProvider.generateToken(authentication), TOKEN_TYPE, user.getId(), user.getUsername());
    }

    public void deleteById(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public LoginDTOResponse login(LoginDTORequest login){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(login.username()).orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        return new LoginDTOResponse(jwtTokenProvider.generateToken(authentication), TOKEN_TYPE, user.getId(), user.getUsername());
    }

    public UserDTOResponse findByUsername(String username) {
        return UserMapper.map(userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Usuário inexistente")));
    }

    public UserDTOResponse updateById(String id, LoginDTORequest request){
        User oldUser = userRepository.findById(IdUtills.transformToUuid(id)).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        oldUser.setUsername(request.username());
        oldUser.setPassword(request.password());
        return UserMapper.map(userRepository.save(oldUser));
    }
}
