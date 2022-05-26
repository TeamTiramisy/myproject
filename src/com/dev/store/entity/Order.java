package com.dev.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    Long id;
    String product;
    Long userId;
    LocalDate dateRegistration;
    LocalDate dateClose;
    Status status;
    Integer total;
}
