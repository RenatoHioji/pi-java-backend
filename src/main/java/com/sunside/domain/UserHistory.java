package com.sunside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserHistory {
    private UUID id;
    private User user;
    private Item item;
    private LocalDateTime last_viewed;
    private Integer viewed;


    public UserHistory() {
    }

    public UserHistory(UUID id, User user, Item item, LocalDateTime last_viewed, Integer viewed) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.last_viewed = last_viewed;
        this.viewed = viewed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getLast_viewed() {
        return last_viewed;
    }

    public void setLast_viewed(LocalDateTime last_viewed) {
        this.last_viewed = last_viewed;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }
}