package com.sellers.infrastructure.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Id {

    private UUID id;

    public Id() {
        this.id = UUID.randomUUID();
    }
}
