package com.sunside.application.repositories;

import com.sunside.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(UUID id);

    List<User> findAll();
}
