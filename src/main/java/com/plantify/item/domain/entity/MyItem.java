package com.plantify.item.domain.entity;

import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long quantity;

    public MyItem updateQuantity(long quantity) {
        if (quantity < 0) {
            throw new ApplicationException(ItemErrorCode.INVALID_ITEM_DATA);
        }
        this.quantity = quantity;
        return this;
    }
}
