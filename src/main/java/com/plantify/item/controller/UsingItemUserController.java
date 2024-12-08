package com.plantify.item.controller;

import com.plantify.item.domain.dto.UsingItemActionInput;
import com.plantify.item.domain.dto.UsingItemOutput;
import com.plantify.item.service.usingItem.UsingItemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsingItemUserController {

    private final UsingItemUserService usingItemUserService;
    private final Sinks.Many<UsingItemOutput> usingItemSink = Sinks.many().multicast().onBackpressureBuffer();

    @QueryMapping
    public List<UsingItemOutput> getAllUsingItemsByUser() {
        return usingItemUserService.getAllUsingItemsByUser();
    }

    @MutationMapping
    public List<UsingItemOutput> manageUsingItems(@Argument List<UsingItemActionInput> actions) {
        List<UsingItemOutput> results = usingItemUserService.manageUsingItems(actions);
        results.forEach(usingItemSink::tryEmitNext);
        return results;
    }

    @SubscriptionMapping
    public Flux<UsingItemOutput> usingItemUpdates(@Argument Long userId) {
        return usingItemSink.asFlux()
                .filter(update -> update.myItemId().equals(userId));
    }
}
