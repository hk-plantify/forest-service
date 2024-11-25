package com.plantify.item.service.item;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.ItemRepository;
import com.plantify.item.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponse> getItemsByCategory(Category category) {
        return itemRepository.findByCategory(category)
                .stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponse addItem(ItemRequest request) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        Item item = request.toEntity(adminId);
        Item savedItem = itemRepository.save(item);

        return ItemResponse.from(savedItem);
    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        Item updatedItem = item.toBuilder()
                .name(request.name())
                .price(request.price())
                .imageUri(request.imageUri())
                .category(Category.valueOf(request.category()))
                .build();
        Item savedItem = itemRepository.save(updatedItem);

        return ItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        itemRepository.delete(item);
    }
}
