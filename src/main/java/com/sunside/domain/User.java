package com.sunside.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String username; //email
    private String password;
    private Set<Item> items = new HashSet<>();
    private Set<Item> history = new HashSet<>();


    public User(UUID id, String username, String password, Set<Item> items, Set<Item> history) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.items = items;
        this.history = history;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getHistory() {
        return history;
    }

    public void setHistory(Set<Item> history) {
        this.history = history;
    }
}
