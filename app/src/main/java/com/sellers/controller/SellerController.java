package com.sellers.controller;

import com.sellers.services.core.SellerService;
import com.sellers.services.dto.SellerDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Validated
@Controller("/seller")
@AllArgsConstructor
public class SellerController {

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    private SellerService service;

    @Post
    public HttpResponse<?> postSeller(@Valid @Body SellerDTO sellerDTO) {
        logger.info("SellerDTO {}", sellerDTO);
        service.save(sellerDTO);
        return HttpResponse.accepted();
    }
}
