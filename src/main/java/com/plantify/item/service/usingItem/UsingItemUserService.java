package com.plantify.item.service.usingItem;

import com.plantify.item.domain.dto.UsingItemActionInput;
import com.plantify.item.domain.dto.UsingItemOutput;

import java.util.List;


public interface UsingItemUserService {

    List<UsingItemOutput> getAllUsingItemsByUser();
    List<UsingItemOutput> manageUsingItems(List<UsingItemActionInput> actions);
}
