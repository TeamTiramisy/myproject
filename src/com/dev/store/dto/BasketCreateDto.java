package com.dev.store.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BasketCreateDto {

    Long users_id;
    Long technic_id;
}
