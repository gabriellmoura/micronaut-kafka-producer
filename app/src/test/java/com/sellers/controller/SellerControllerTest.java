package com.sellers.controller;

import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.domain.Seller;
import com.sellers.infrastructure.kafka.SellerPublisher;
import com.sellers.services.dto.SellerDTO;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@MicronautTest
class SellerControllerTest {

    @MockBean(SellerPublisher.class)
    SellerPublisher publisherMockBean() {
        return mock(SellerPublisher.class);
    }

    @Inject
    private SellerPublisher sellerPublisher;

    @Inject
    @Client("/")
    private Rx3HttpClient client;

    @Test
    @DisplayName("It should post the seller in topic to save successfully")
    void saveSeller() {
        var sellerDTO = SellerDTO.builder().address("St Row street").name("666").telephone("0000000000").build();
        HttpRequest request = HttpRequest.POST("/seller", sellerDTO);

        doNothing().when(sellerPublisher).publish(any(Id.class), any(Seller.class));
        HttpResponse<?> response =
                client.toBlocking().exchange(request, SellerDTO.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatus());
    }
}
