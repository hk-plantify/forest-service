package com.plantify.item.service.myItem;

import com.plantify.item.client.CashServiceClient;
import com.plantify.item.domain.dto.request.CashRequest;
import com.plantify.item.domain.dto.request.MyItemPurchaseRequest;
import com.plantify.item.domain.dto.request.MyItemUserRequest;
import com.plantify.item.domain.dto.response.CashResponse;
import com.plantify.item.domain.dto.response.MyItemUserResponse;
import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.CashErrorCode;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.repository.ItemRepository;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyItemUserServiceImpl implements MyItemUserService {

    private final MyItemRepository myItemRepository;
    private final ItemRepository itemRepository;
    private final UserInfoProvider userInfoProvider;
    private final CashServiceClient cashServiceClient;

    @Override
    public List<MyItemUserResponse> getMyItemsByUser() {
        Long userId = userInfoProvider.getUserInfo().userId();
        return myItemRepository.findByUserId(userId)
                .stream()
                .map(MyItemUserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MyItemUserResponse purchaseItem(MyItemPurchaseRequest request) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ApplicationException(ItemErrorCode.ITEM_NOT_FOUND));

        CashRequest cashRequest = new CashRequest(item.getPrice() * request.quantity(), "USAGE" );
        ApiResponse<CashResponse> cashResponse = cashServiceClient.buyByCash(cashRequest);

        if (cashResponse.getStatus() != 200) {
            throw new ApplicationException(CashErrorCode.INSUFFICIENT_BALANCE);
        }

        MyItem myItem = request.toEntity(userId, item);
        myItemRepository.save(myItem);

        return MyItemUserResponse.from(myItem);
    }
}