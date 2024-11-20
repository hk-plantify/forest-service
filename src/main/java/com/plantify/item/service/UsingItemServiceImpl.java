package com.plantify.item.service;

import com.plantify.item.domain.dto.request.UsingItemRequest;
import com.plantify.item.domain.dto.response.UsingItemResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.repository.UsingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsingItemServiceImpl implements UsingItemService {

    private final MyItemRepository myItemRepository;
    private final UsingItemRepository usingItemRepository;

    @Override
    public List<UsingItemResponse> getAllUsingItems() {
        if (authenticationService.validateAdminRole()) {
            return usingItemRepository.findAll()
                    .stream()
                    .map(UsingItemResponse::from)
                    .collect(Collectors.toList());
        }

        Long userId = authenticationService.getUserId();
        return usingItemRepository.findByUserId(userId)
                .stream()
                .map(UsingItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UsingItemResponse addUsingItem(UsingItemRequest request) {
        Long userId = authenticationService.getUserId();
        MyItem myItem = myItemRepository.findById(request.myItemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        UsingItem usingItem = request.toEntity(myItem);
        UsingItem savedItem = usingItemRepository.save(usingItem);

        return UsingItemResponse.from(savedItem);
    }

    @Override
    public UsingItemResponse updateUsingItem(Long usingItemId, UsingItemRequest request) {
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        Long userId = authenticationService.getUserId();

        UsingItem updatedUsingItem = usingItem.toBuilder()
                .posX(request.posX())
                .posY(request.posY())
                .build();
        UsingItem savedUsingItem = usingItemRepository.save(updatedUsingItem);

        return UsingItemResponse.from(savedUsingItem);
    }

    @Override
    public void deleteUsingItem(Long usingItemId) {
        Long userId = authenticationService.getUserId();
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        usingItemRepository.delete(usingItem);

    }
}


