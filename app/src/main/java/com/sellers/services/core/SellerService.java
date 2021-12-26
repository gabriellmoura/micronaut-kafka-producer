package com.sellers.services.core;

import com.sellers.infrastructure.domain.Id;
import com.sellers.infrastructure.kafka.SellerPublisher;
import com.sellers.services.dto.SellerDTO;
import com.sellers.services.mapper.SellerMapper;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class SellerService {

    private SellerMapper sellerMapper;

    private SellerPublisher sellerPublisher;

    public void save(SellerDTO sellerDTO) {
        var id = new Id();
        var seller = sellerMapper.map(sellerDTO);
        sellerPublisher.publish(id, seller);
    }
}
