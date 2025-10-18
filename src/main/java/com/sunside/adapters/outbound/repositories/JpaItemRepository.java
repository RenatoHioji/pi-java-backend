package com.sunside.adapters.outbound.repositories;

import com.sunside.adapters.outbound.entities.JpaItemEntity;
import com.sunside.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaItemRepository extends JpaRepository<JpaItemEntity, UUID> {
    List<JpaItemEntity> findAllByUserId(UUID userId);
}
