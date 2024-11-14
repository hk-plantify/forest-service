package com.plantify.item.controller;

import com.plantify.item.service.MyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items")
public class MyItemController {

    private final MyItemService myItemService;
}
