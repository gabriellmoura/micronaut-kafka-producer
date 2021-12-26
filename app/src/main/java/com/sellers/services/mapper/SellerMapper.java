package com.sellers.services.mapper;

import com.sellers.infrastructure.domain.Seller;
import com.sellers.services.dto.SellerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface SellerMapper {
    Seller map(SellerDTO source);
}
