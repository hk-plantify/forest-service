package com.plantify.item.repository;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.MyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyItemRepository extends JpaRepository<MyItem, Long> {

    List<MyItem> findByUserId(Long userId);
    Optional<MyItem> findMyItemByMyItemIdAndUserId(Long myItemId, Long userId);
    List<MyItem> findByItemCategoryAndUserId(Category category, Long userId);
    Optional<MyItem> findByUserIdAndItemItemId(Long userId, Long itemItemId);
}
