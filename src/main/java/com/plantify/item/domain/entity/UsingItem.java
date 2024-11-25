package com.plantify.item.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UsingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long usingItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myItemId", nullable = false)
    private MyItem myItem;

    @Column(nullable = false)
    private Double posX;

    @Column(nullable = false)
    private Double posY;
}
