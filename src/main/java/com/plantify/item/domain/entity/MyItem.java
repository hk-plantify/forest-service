package com.plantify.item.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long myItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Status status;
}
