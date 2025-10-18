package com.sunside.application.repositories;

import com.sunside.domain.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserHistoryRepository {

    List<Item> findRecents(@Param("user_id") UUID userId);

    Optional<Item> findByUserIdAndItemId(@Param("user_id") UUID userId, @Param("item_id") UUID itemId);

    List<Item> findMoreViewed(@Param("user_id") UUID userId);

    Item save(Item item);
}
