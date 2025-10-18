package com.sunside.adapters.outbound.repositories;

import com.sunside.adapters.outbound.entities.JpaItemEntity;
import com.sunside.adapters.outbound.entities.JpaUserHistoryEntity;
import com.sunside.domain.Item;
import com.sunside.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaUserHistoryRepository extends JpaRepository<JpaUserHistoryEntity, UUID> {

    @Query("SELECT i FROM UserHistory uh JOIN uh.item i WHERE uh.user.id = :user_id ORDER BY uh.last_viewed DESC")
    List<JpaItemEntity> findRecents(@Param("user_id") UUID user_id);

    @Query("SELECT uh FROM UserHistory uh WHERE uh.user.id = :user_id AND uh.item.id = :item_id")
    Optional<JpaUserHistoryEntity> findByUserIdAndItemId(@Param("user_id") UUID user_id, @Param("item_id") UUID itemId);

    @Query("SELECT i FROM UserHistory uh JOIN uh.item i WHERE uh.user.id = :user_id ORDER BY uh.viewed DESC")
    List<JpaItemEntity> findMoreViewed(@Param("user_id") UUID user_id);
}
