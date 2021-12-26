package com.sellers.common;

import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.domain.Seller;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@KafkaListener(groupId = "sellers.test.listener")
public class SellersTestListener {

    private CountDownLatch latch = new CountDownLatch(1);

    private Map<Id, Seller> events = new HashMap<>();

    @Topic("${kafka.topic.sellers}")
    public void publishedSeller(@KafkaKey Id id, Seller seller) {
        events.put(id, seller);
        latch.countDown();
    }

    public CountDownLatch getLatch() { return latch; }

    public Map<Id, Seller> getEvents() { return events; }
}
