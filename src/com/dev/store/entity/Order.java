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

    private Long id;
    private String product;
    private Long userId;
    private LocalDate dateRegistration;
    private LocalDate dateClose;
    private Status status;
    private Integer total;
}
