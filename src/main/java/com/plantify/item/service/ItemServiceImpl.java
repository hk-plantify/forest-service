package com.plantify.item.service;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse addItem(ItemRequest request) {
        Long userId = authenticationService.getUserId();
        authenticationService.validateAdminRole();
        Item item = request.toEntity(userId);
        Item savedItem = itemRepository.save(item);
        return ItemResponse.from(savedItem);
    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest request) {
        authenticationService.validateAdminRole();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        Item updatedItem = item.toBuilder()
                .name(request.name())
                .price(request.price())
                .imageUri(request.imageUri())
                .category(request.category())
                .build();

        Item savedItem = itemRepository.save(updatedItem);
        return ItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(Long itemId) {
        authenticationService.validateAdminRole();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        itemRepository.delete(item);
    }
}
