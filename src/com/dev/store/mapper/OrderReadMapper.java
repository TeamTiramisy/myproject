package com.dev.store.mapper;

import com.dev.store.dto.OrderReadDto;
import com.dev.store.entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private static final OrderReadMapper INSTANCE = new OrderReadMapper();

    @Override
    public OrderReadDto map(Order object) {
        return OrderReadDto.builder()
                .id(object.getId())
                .product(object.getProduct())
                .userId(object.getUserId())
                .dateRegistration(object.getDateRegistration())
                .dateClose(object.getDateClose())
                .status(object.getStatus())
                .total(object.getTotal())
                .build();
    }

    public static OrderReadMapper getInstance(){
        return INSTANCE;
    }
}
