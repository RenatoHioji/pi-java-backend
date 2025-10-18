package com.sunside.adapters.outbound.repositories;

import com.sunside.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaGameRepository extends JpaRepository<Game, UUID> {
}
