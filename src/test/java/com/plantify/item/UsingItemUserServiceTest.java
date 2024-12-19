package com.plantify.item;

import com.plantify.item.domain.dto.UsingItemActionInput;
import com.plantify.item.domain.dto.UsingItemOutput;
import com.plantify.item.domain.dto.response.AuthUserResponse;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;
import com.plantify.item.repository.MyItemRepository;
import com.plantify.item.repository.UsingItemRepository;
import com.plantify.item.service.usingItem.UsingItemUserService;
import com.plantify.item.global.util.UserInfoProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UsingItemUserServiceTest {

    @MockBean
    private UsingItemRepository usingItemRepository;

    @MockBean
    private MyItemRepository myItemRepository;

    @MockBean
    private UserInfoProvider userInfoProvider;

    @Autowired
    private UsingItemUserService usingItemUserService;

    @Test
    void testGetAllUsingItemsByUser() {
        // Given
        Long userId = 1L;

        UsingItem item = UsingItem.builder()
                .usingItemId(1L)
                .myItem(MyItem.builder()
                        .myItemId(10L)
                        .userId(userId)
                        .build())
                .posX(12.34)
                .posY(56.78)
                .build();

        given(userInfoProvider.getUserInfo()).willReturn(new AuthUserResponse(userId, "USER"));
        given(usingItemRepository.findByUserId(userId)).willReturn(List.of(item));

        // When
        List<UsingItemOutput> result = usingItemUserService.getAllUsingItemsByUser();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).posX()).isEqualTo(12.34);
    }

    @Test
    void testCreateUsingItem() {
        // Given
        Long userId = 1L;
        UsingItemActionInput actionInput = new UsingItemActionInput("CREATE", null, 10L, 12.34, 56.78);

        MyItem myItem = MyItem.builder()
                .myItemId(10L)
                .userId(userId)
                .build();

        UsingItem newItem = UsingItem.builder()
                .usingItemId(1L)
                .myItem(myItem)
                .posX(12.34)
                .posY(56.78)
                .build();

        given(userInfoProvider.getUserInfo()).willReturn(new AuthUserResponse(userId, "USER"));
        given(myItemRepository.findMyItemByMyItemIdAndUserId(10L, userId)).willReturn(Optional.of(myItem));
        given(usingItemRepository.save(any(UsingItem.class))).willReturn(newItem);

        // When
        List<UsingItemOutput> result = usingItemUserService.manageUsingItems(List.of(actionInput));

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).posX()).isEqualTo(12.34);
    }

    @Test
    void testUpdateUsingItem() {
        // Given
        Long userId = 1L;
        UsingItemActionInput actionInput = new UsingItemActionInput("UPDATE", 1L, null, 90.12, 34.56);

        UsingItem existingItem = UsingItem.builder()
                .usingItemId(1L)
                .myItem(MyItem.builder()
                        .myItemId(10L)
                        .userId(userId)
                        .build())
                .posX(12.34)
                .posY(56.78)
                .build();

        UsingItem updatedItem = existingItem.toBuilder()
                .posX(90.12)
                .posY(34.56)
                .build();

        given(userInfoProvider.getUserInfo()).willReturn(new AuthUserResponse(userId, "USER"));
        given(usingItemRepository.findByUsingItemIdAndUserId(1L, userId)).willReturn(Optional.of(existingItem));
        given(usingItemRepository.save(any(UsingItem.class))).willReturn(updatedItem);

        // When
        List<UsingItemOutput> result = usingItemUserService.manageUsingItems(List.of(actionInput));

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).posX()).isEqualTo(90.12);
        assertThat(result.get(0).posY()).isEqualTo(34.56);
    }

    @Test
    void testDeleteUsingItem() {
        // Given
        Long userId = 1L;
        UsingItemActionInput actionInput = new UsingItemActionInput("DELETE", 1L, null, null, null);

        UsingItem existingItem = UsingItem.builder()
                .usingItemId(1L)
                .myItem(MyItem.builder()
                        .myItemId(10L)
                        .userId(userId)
                        .build())
                .posX(12.34)
                .posY(56.78)
                .build();

        given(userInfoProvider.getUserInfo()).willReturn(new AuthUserResponse(userId, "USER"));
        given(usingItemRepository.findByUsingItemIdAndUserId(1L, userId)).willReturn(Optional.of(existingItem));

        // When
        List<UsingItemOutput> result = usingItemUserService.manageUsingItems(List.of(actionInput));

        // Then
        assertThat(result).isEmpty();
        verify(usingItemRepository, times(1)).deleteById(1L);
    }
}

