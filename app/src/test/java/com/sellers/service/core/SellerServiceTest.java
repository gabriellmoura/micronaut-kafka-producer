package com.sellers.service.core;

import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.domain.Seller;
import com.sellers.infrastructure.kafka.SellerPublisher;
import com.sellers.services.core.SellerService;
import com.sellers.services.dto.SellerDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@MicronautTest
class SellerServiceTest {

    @MockBean(SellerPublisher.class)
    SellerPublisher publisherMockBean() {
        return mock(SellerPublisher.class);
    }
    @Inject
    private SellerPublisher sellerPublisher;

    @Inject
    private SellerService sellerService;

    @Test
    @DisplayName("It should publish seller successfully into kafka topic")
    void shouldSaveSuccessfully() throws InterruptedException {
        var sellerDTO = SellerDTO.builder().address("St Row street").name("666").telephone("0000000001").build();
        doNothing().when(sellerPublisher).publish(any(Id.class), any(Seller.class));

        sellerService.save(sellerDTO);

        Mockito.verify(sellerPublisher, times(1)).publish(any(Id.class), any(Seller.class));
    }
}
