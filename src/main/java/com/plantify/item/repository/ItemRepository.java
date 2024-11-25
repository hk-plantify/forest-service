package com.plantify.item.repository;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCategory(Category category);
}
