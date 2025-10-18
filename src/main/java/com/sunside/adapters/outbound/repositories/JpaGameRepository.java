package com.sunside.adapters.outbound.repositories;

import com.sunside.adapters.outbound.entities.JpaGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaGameRepository extends JpaRepository<JpaGameEntity, UUID> {
}
