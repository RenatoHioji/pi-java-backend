package com.sunside.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "items")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

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
}
