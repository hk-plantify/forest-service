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
    private final InternalService internalService;

    @Override
    public List<MyItemResponse> getAllMyItems() {
        if (authenticationService.validateAdminRole()) {
            return myItemRepository.findAll()
                    .stream()
                    .map(MyItemResponse::from)
                    .collect(Collectors.toList());
        }

        Long userId = authenticationService.getUserId();
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MyItemResponse addMyItem(MyItemRequest request) {
        Long userId = authenticationService.getUserId();
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        MyItem myItem = request.toEntity(userId, item);
        MyItem savedItem = myItemRepository.save(myItem);

        internalService.recordActivityLog("ITEM", savedItem.getMyItemId(), "CREATE", userId);

        return MyItemResponse.from(savedItem);
    }

    @Override
    public MyItemResponse updateMyItem(Long myItemId, MyItemRequest request) {
        MyItem myItem = myItemRepository.findById(myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(myItem.getUserId());

        Long userId = authenticationService.getUserId();

        MyItem updatedItem = myItem.toBuilder()
                .status(request.status())
                .build();
        MyItem savedItem = myItemRepository.save(updatedItem);

        internalService.recordActivityLog("ITEM", myItemId, "UPDATE", userId);

        return MyItemResponse.from(savedItem);
    }

    @Override
    public void deleteItem(Long myItemId) {
        Long userId = authenticationService.getUserId();
        MyItem myItem = myItemRepository.findById(myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(myItem.getUserId());

        myItemRepository.delete(myItem);

        internalService.recordActivityLog("ITEM", myItemId, "DELETE", userId);
    }
}
