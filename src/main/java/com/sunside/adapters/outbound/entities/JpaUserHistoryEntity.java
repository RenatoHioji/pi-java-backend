package com.sunside.adapters.outbound.entities;

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
public class JpaUserHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUserEntity jpaUserEntity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private JpaItemEntity item;

    @Column(name = "last_viewed")
    private LocalDateTime last_viewed;

    private Integer viewed;
}