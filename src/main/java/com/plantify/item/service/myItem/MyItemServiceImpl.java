package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.global.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyItemServiceImpl implements MyItemService {

    private final MyItemRepository myItemRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public List<MyItemResponse> getMyItemsByUser() {
        Long userId = userInfoProvider.getUserInfo().userId();
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemResponse::from)
                .toList();
    }

    @Override
    public List<MyItemResponse> getAllMyItems() {
        return myItemRepository.findAll()
                .stream()
                .map(MyItemResponse::from)
                .toList();
    }

    @Override
    public void deleteMyItem(Long myItemId) {
        MyItem myItem = myItemRepository.findByUserId(myItemId)
                .stream().findFirst()
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        myItemRepository.delete(myItem);
    }

    @Override
    public List<MyItemResponse> getMyItemsByUserId(Long userId) {
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemResponse::from)
                .toList();
    }
}