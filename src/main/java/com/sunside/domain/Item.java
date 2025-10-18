package com.sunside.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Item {

    private UUID id;
    private String name;
    private String syllables;
    private String image;
    private String video;
    private String audio;
    private String category;
    private String subcategory;

    private UUID userId;

    private Set<User> usersHistory = new HashSet<>();

    public Item() {
    }

    public Item(UUID id, String name, String syllables, String image, String video, String audio, String category, String subcategory, UUID userId, Set<User> usersHistory) {
        this.id = id;
        this.name = name;
        this.syllables = syllables;
        this.image = image;
        this.video = video;
        this.audio = audio;
        this.category = category;
        this.subcategory = subcategory;
        this.userId = userId;
        this.usersHistory = usersHistory;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyllables() {
        return syllables;
    }

    public void setSyllables(String syllables) {
        this.syllables = syllables;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Set<User> getUsersHistory() {
        return usersHistory;
    }

    public void setUsersHistory(Set<User> usersHistory) {
        this.usersHistory = usersHistory;
    }
}
