package com.plantify.item.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long currentItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseItemId", nullable = false)
    private PurchaseItem purchaseItem;

    @Column(nullable = false)
    private Double posX;

    @Column(nullable = false)
    private Double posY;
}
