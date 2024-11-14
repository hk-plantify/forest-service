package com.plantify.item.service;

import com.plantify.item.domain.dto.request.MyItemRequest;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.ItemRepository;
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
        if (authenticationService.validateAdminRole(authorizationHeader)) {
            return myItemRepository.findAll()
                    .stream()
                    .map(MyItemResponse::from)
                    .collect(Collectors.toList());
        }

        Long kakaoId = authenticationService.getKakaoId(authorizationHeader);
        return myItemRepository.findByUserId(kakaoId)
                .stream()
                .map(MyItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MyItemResponse addMyItem(String authorizationHeader, MyItemRequest request) {
        Long kakaoId = authenticationService.getKakaoId(authorizationHeader);
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        MyItem myItem = request.toEntity(kakaoId, item);
        MyItem savedItem = myItemRepository.save(myItem);
        return MyItemResponse.from(savedItem);
    }

    @Override
    public MyItemResponse updateMyItem(String authorizationHeader, Long myItemId, MyItemRequest request) {
        MyItem myItem = myItemRepository.findById(myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(authorizationHeader, myItem.getUserId());

        MyItem updatedItem = MyItem.builder()
                .myItemId(myItem.getMyItemId())
                .item(myItem.getItem())
                .userId(myItem.getUserId())
                .status(request.status())
                .createdAt(myItem.getCreatedAt())
                .updatedAt(myItem.getUpdatedAt())
                .build();

        MyItem savedItem = myItemRepository.save(updatedItem);
        return MyItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(String authorizationHeader, Long myItemId) {
        MyItem myItem = myItemRepository.findById(myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(authorizationHeader, myItem.getUserId());
        myItemRepository.delete(myItem);
    }
}
