package com.sunside.adapters.outbound.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "items")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JpaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String syllables;
    private String image;
    private String video;
    private String audio;
    private String category;
    private String subcategory;

    @Column(name = "user_id")
    private UUID userId;


    @ManyToMany(mappedBy = "history")
    @Builder.Default
    @JsonIgnore
    private Set<JpaUserEntity> usersHistory = new HashSet<>();
}
