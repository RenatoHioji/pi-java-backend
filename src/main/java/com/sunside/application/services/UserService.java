package com.sunside.application.services;

import com.sunside.adapters.inbound.dto.LoginDTORequest;
import com.sunside.adapters.outbound.dto.LoginDTOResponse;
import com.sunside.adapters.inbound.dto.UserDTORequest;
import com.sunside.adapters.outbound.dto.UserDTOResponse;
import com.sunside.infrastructure.exceptions.BusinessException;
import com.sunside.utils.mappers.UserMapper;
import com.sunside.domain.User;
import com.sunside.adapters.outbound.repositories.JpaUserRepository;
import com.sunside.infrastructure.security.JwtTokenProvider;
import com.sunside.utils.IdUtills;
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
    private final JpaUserRepository userRepository;
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

    public LoginDTOResponse updateById(String id, LoginDTORequest request){
        User oldUser = userRepository.findById(IdUtills.transformToUuid(id)).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        if(userRepository.findByUsername(request.username()).isPresent()){
            throw new BusinessException("Usuário existente");
        }
        oldUser.setUsername(request.username());
        oldUser.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(oldUser);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginDTOResponse(jwtTokenProvider.generateToken(authentication), TOKEN_TYPE, oldUser.getId(), request.username());

    }
}
