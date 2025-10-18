package com.sunside.infrastructure.security;

import com.sunside.infrastructure.exceptions.BusinessException;
import com.sunside.domain.User;
import com.sunside.adapters.outbound.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws BusinessException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(("User not found with username or email: " + username)));
        Set<GrantedAuthority> authorities = Stream.of(new SimpleGrantedAuthority("ADMIN")).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}