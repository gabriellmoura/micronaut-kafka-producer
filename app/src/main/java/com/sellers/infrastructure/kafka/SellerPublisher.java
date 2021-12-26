package com.sellers.infrastructure.kafka;

import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.domain.Seller;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface SellerPublisher {

    @Topic("${kafka.topic.sellers}")
    void publish(@KafkaKey Id id, Seller seller);

}
