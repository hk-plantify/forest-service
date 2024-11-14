package com.plantify.item.service;

import com.plantify.item.domain.dto.request.MyItemRequest;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.PurchaseItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.repository.MyItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyItemServiceImpl implements MyItemService {

    private final MyItemRepository myItemRepository;
    private final ItemRepository itemRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<MyItemResponse> getAllMyItems(String authorizationHeader) {
        Long kakaoId = authenticationService.getUserInfo(authorizationHeader).kakaoId();
        return myItemRepository.findByKakaoId(kakaoId)
                .stream()
                .map(MyItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MyItemResponse addMyItem(String authorizationHeader, MyItemRequest request) {
        Long kakaoId = authenticationService.getUserInfo(authorizationHeader).kakaoId();
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        PurchaseItem myItem = request.toEntity(kakaoId, item);
        PurchaseItem savedItem = myItemRepository.save(myItem);
        return MyItemResponse.from(savedItem);
    }

    @Override
    public MyItemResponse updateMyItem(String authorizationHeader, Long myItemId, MyItemRequest request) {
        Long kakaoId = authenticationService.getUserInfo(authorizationHeader).kakaoId();
        PurchaseItem myItem = myItemRepository.findByKakaoIdAndMyItemId(kakaoId, myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        PurchaseItem updatedItem = PurchaseItem.builder()
                .purchaseItemId(myItem.getPurchaseItemId())
                .item(myItem.getItem())
                .userId(myItem.getUserId())
                .purchaseDate(myItem.getPurchaseDate())
                .status(request.status())
                .build();

        PurchaseItem savedItem = myItemRepository.save(updatedItem);
        return MyItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(String authorizationHeader, Long myItemId) {
        Long kakaoId = authenticationService.getUserInfo(authorizationHeader).kakaoId();
        PurchaseItem myItem = myItemRepository.findByKakaoIdAndMyItemId(kakaoId, myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        myItemRepository.delete(myItem);
    }
}
