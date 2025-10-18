package com.sunside.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer correctAnswer;
    private Integer type;

    @Column(name = "quiz_id")
    private UUID quizId;

    @ManyToMany
    @JoinTable(
            name = "game_item",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @OrderBy("id ASC")
    @Builder.Default
    private Set<Item> items = new HashSet<>();
}
