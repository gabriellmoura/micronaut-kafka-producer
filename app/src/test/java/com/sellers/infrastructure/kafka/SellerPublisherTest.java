package com.sellers.infrastructure.kafka;

import com.sellers.common.KafkaBaseTest;
import com.sellers.common.SellersTestListener;
import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.domain.Seller;
import com.sellers.services.dto.SellerDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SellerPublisherTest extends KafkaBaseTest {
    @Inject
    @Client("/")
    private Rx3HttpClient client;

    @Inject
    private SellersTestListener sellersTestListener;

    @Test
    @DisplayName("It should post the seller in topic to save successfully")
    void shouldPublishSellerIntoTopicSuccessfully() throws InterruptedException {
        var sellerDTO = SellerDTO.builder().address("St Row street").name("666").telephone("0000000000").build();
        HttpRequest request = HttpRequest.POST("/seller", sellerDTO);

        HttpResponse<?> response =
                client.toBlocking().exchange(request, SellerDTO.class);
        sellersTestListener.getLatch().await(1, TimeUnit.SECONDS);

        assertEquals(HttpStatus.ACCEPTED, response.getStatus());
        Map<Id, Seller> events = sellersTestListener.getEvents();
        assertEquals(1, events.size());
    }
}
