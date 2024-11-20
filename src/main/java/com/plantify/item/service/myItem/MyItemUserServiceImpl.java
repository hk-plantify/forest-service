package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.request.MyItemUserRequest;
import com.plantify.item.domain.dto.response.MyItemUserResponse;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.ItemRepository;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyItemUserServiceImpl implements MyItemUserService {

    private final MyItemRepository myItemRepository;
    private final ItemRepository itemRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public List<MyItemUserResponse> getMyItemsByUser() {
        Long userId = userInfoProvider.getUserInfo().userId();
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemUserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MyItemUserResponse createMyItem(MyItemUserRequest request) {
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        MyItem myItem = request.toEntity(item);
        myItemRepository.save(myItem);

        return MyItemUserResponse.from(myItem);
    }
}