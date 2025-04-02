package com.sunside.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_history")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "last_viewed")
    private LocalDateTime last_viewed;

    private Integer viewed;
}