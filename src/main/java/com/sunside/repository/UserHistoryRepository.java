package com.sunside.repository;

import com.sunside.model.Item;
import com.sunside.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserHistoryRepository extends JpaRepository<UserHistory, UUID> {

    @Query("SELECT i FROM UserHistory uh JOIN uh.item i WHERE uh.user.id = :user_id ORDER BY uh.last_viewed DESC")
    List<Item> findRecents(@Param("user_id") UUID user_id);

    @Query("SELECT uh FROM UserHistory uh WHERE uh.user.id = :user_id AND uh.item.id = :item_id")
    Optional<UserHistory> findByUserIdAndItemId(@Param("user_id") UUID user_id, @Param("item_id") UUID itemId);

    @Query("SELECT i FROM UserHistory uh JOIN uh.item i WHERE uh.user.id = :user_id ORDER BY uh.viewed DESC")
    List<Item> findMoreViewed(@Param("user_id") UUID user_id);
}
