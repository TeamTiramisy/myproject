package com.dev.store.dto;

import com.dev.store.entity.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OrderCreateDto {

    String product;
    Long userId;
    String dateRegistration;
    String dateClose;
    String status;
    Integer total;
}
