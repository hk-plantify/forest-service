package com.plantify.item.repository;

import com.plantify.item.domain.entity.UsingItem;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsingItemRepository extends JpaRepository<UsingItem, Long> {

    @Query("SELECT u FROM UsingItem u WHERE u.myItem.userId = :userId")
    List<UsingItem> findByUserId(@Param("userId") Long userId);
}
