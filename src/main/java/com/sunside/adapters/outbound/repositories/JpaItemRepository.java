package com.sunside.adapters.outbound.repositories;

import com.sunside.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByUserId(UUID userId);
}
