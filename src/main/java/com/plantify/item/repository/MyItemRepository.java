package com.plantify.item.repository;

import com.plantify.item.domain.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyItemRepository extends JpaRepository<PurchaseItem, Long> {

    List<PurchaseItem> findByKakaoId(Long kakaoId);
    Optional<PurchaseItem> findByKakaoIdAndMyItemId(Long kakaoId, Long purchaseItemId);
}
