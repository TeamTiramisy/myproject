package com.dev.store.dto;

import com.dev.store.entity.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OrderReadDto {

    Long id;
    String product;
    Long userId;
    LocalDate dateRegistration;
    LocalDate dateClose;
    Status status;
    Integer total;
}
