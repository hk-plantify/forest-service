package com.plantify.item.service;

import com.plantify.item.domain.dto.request.UsingItemRequest;
import com.plantify.item.domain.dto.response.UsingItemResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.ItemRepository;
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
    private final AuthenticationService authenticationService;
    private final ItemRepository itemRepository;

    @Override
    public List<UsingItemResponse> getAllUsingItems(String authorizationHeader) {
        if (authenticationService.validateAdminRole(authorizationHeader)) {
            return usingItemRepository.findAll()
                    .stream()
                    .map(UsingItemResponse::from)
                    .collect(Collectors.toList());
        }

        Long kakaoId = authenticationService.getKakaoId(authorizationHeader);
        return usingItemRepository.findByUserId(kakaoId)
                .stream()
                .map(UsingItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UsingItemResponse addUsingItem(String authorizationHeader, UsingItemRequest request) {
        Long kakaoId = authenticationService.getKakaoId(authorizationHeader);
        MyItem myItem = myItemRepository.findById(request.myItemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        authenticationService.validateOwnership(authorizationHeader, myItem.getUserId());
        UsingItem usingItem = request.toEntity(myItem);
        UsingItem savedUsingItem = usingItemRepository.save(usingItem);
        return UsingItemResponse.from(savedUsingItem);
    }

    @Override
    public UsingItemResponse updateUsingItem(String authorizationHeader, Long usingItemId, UsingItemRequest request) {
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(authorizationHeader, usingItem.getMyItem().getUserId());

        usingItem = UsingItem.builder()
                .usingItemId(usingItem.getUsingItemId())
                .myItem(usingItem.getMyItem())
                .posX(request.posX())
                .posY(request.posY())
                .build();

        UsingItem savedUsingItem = usingItemRepository.save(usingItem);
        return UsingItemResponse.from(savedUsingItem);
    }

    @Override
    public void deleteUsingItem(String authorizationHeader, Long usingItemId) {
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        authenticationService.validateOwnership(authorizationHeader, usingItem.getMyItem().getUserId());
        usingItemRepository.delete(usingItem);
    }
}
