package com.plantify.forest.service;

import com.plantify.forest.domain.dto.request.ItemRequest;
import com.plantify.forest.domain.dto.response.ItemResponse;
import com.plantify.forest.domain.entity.Item;
import com.plantify.forest.global.exception.ApplicationException;
import com.plantify.forest.global.exception.errorcode.ItemErrorCode;
import com.plantify.forest.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse addItem(String authorizationHeader, ItemRequest request) {
        Item item = request.toEntity();
        Item savedItem = itemRepository.save(item);
        return ItemResponse.from(savedItem);
    }

    @Override
    public ItemResponse updateItem(String authorizationHeader, Long itemId, ItemRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        Item updatedItem = Item.builder()
                .itemId(item.getItemId())
                .name(request.name())
                .price(request.price())
                .imageUri(request.imageUri())
                .category(request.category())
                .build();

        Item savedItem = itemRepository.save(updatedItem);
        return ItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(String authorizationHeader, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        itemRepository.delete(item);
    }
}
