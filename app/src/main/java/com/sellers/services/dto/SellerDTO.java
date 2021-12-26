package com.sellers.services.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Builder
public class SellerDTO {
    private String id;
    @NotEmpty(message = "name must be filled up")
    private String name;
    @NotEmpty(message = "telephone must be filled up")
    @Pattern(regexp = "^(\\d{2})\\D*(\\d{5}|\\d{4})\\D*(\\d{4})$", message = "Invalid Brazilian telephone. E.g.: 000000-0000 or 0000000-0000")
    private String telephone;
    @NotEmpty(message = "address must be filled up")
    private String address;

}
