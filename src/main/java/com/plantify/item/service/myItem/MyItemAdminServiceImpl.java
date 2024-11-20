package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.response.MyItemAdminResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.repository.MyItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyItemAdminServiceImpl implements MyItemAdminService {

    private final MyItemRepository myItemRepository;

    @Override
    public List<MyItemAdminResponse> getAllMyItems() {
        return myItemRepository.findAll()
                .stream()
                .map(MyItemAdminResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMyItem(Long myItemId) {
        MyItem myItem = myItemRepository.findByUserId(myItemId)
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));
        myItemRepository.delete(myItem);
    }

    @Override
    public List<MyItemAdminResponse> getMyItemsByUserId(Long userId) {
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemAdminResponse::from)
                .collect(Collectors.toList());
    }
}