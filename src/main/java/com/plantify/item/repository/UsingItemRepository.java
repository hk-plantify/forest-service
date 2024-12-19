package com.plantify.item.repository;

import com.plantify.item.domain.entity.UsingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsingItemRepository extends JpaRepository<UsingItem, Long> {

    @Query("SELECT u FROM UsingItem u WHERE u.myItem.userId = :userId")
    List<UsingItem> findByUserId(Long userId);
    @Query("SELECT u FROM UsingItem u WHERE u.usingItemId = :usingItemId AND u.myItem.userId = :userId")
    Optional<UsingItem> findByUsingItemIdAndUserId(Long usingItemId, Long userId);
    Long countByMyItem_MyItemId(Long myItemId);
}
