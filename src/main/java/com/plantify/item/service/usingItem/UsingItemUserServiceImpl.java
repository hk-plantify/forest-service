package com.plantify.item.service.usingItem;

import com.plantify.item.domain.dto.request.UsingItemUserRequest;
import com.plantify.item.domain.dto.response.UsingItemUserResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.repository.UsingItemRepository;
import com.plantify.item.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsingItemUserServiceImpl implements UsingItemUserService {

    private final UsingItemRepository usingItemRepository;
    private final MyItemRepository myItemRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public List<UsingItemUserResponse> getAllUsingItemsByUser() {
        Long userId = userInfoProvider.getUserInfo().userId();
        return usingItemRepository.findByUserId(userId)
                .stream()
                .map(UsingItemUserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UsingItemUserResponse createUsingItem(UsingItemUserRequest request) {
        MyItem myItem = myItemRepository.findById(request.myItemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        UsingItem usingItem = request.toEntity(myItem);
        UsingItem savedItem = usingItemRepository.save(usingItem);

        return UsingItemUserResponse.from(savedItem);
    }

    @Override
    public UsingItemUserResponse updateUsingItemPos(Long usingItemId, UsingItemUserRequest request) {
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        UsingItem updatedUsingItem = usingItem.toBuilder()
                .posX(request.posX())
                .posY(request.posY())
                .build();

        UsingItem savedUsingItem = usingItemRepository.save(updatedUsingItem);
        return UsingItemUserResponse.from(savedUsingItem);
    }

    @Override
    public void deleteUsingItem(Long usingItemId) {
        UsingItem usingItem = usingItemRepository.findById(usingItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        usingItemRepository.delete(usingItem);
    }
}
