package com.dev.store.mapper;

import com.dev.store.dto.OrderCreateDto;
import com.dev.store.entity.Order;
import com.dev.store.entity.Status;
import com.dev.store.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateMapper implements Mapper<OrderCreateDto, Order> {

    private static final OrderCreateMapper INSTANCE = new OrderCreateMapper();

    @Override
    public Order map(OrderCreateDto object) {
        return Order.builder()
                .product(object.getProduct())
                .userId(object.getUserId())
                .dateRegistration(LocalDateFormatter.format(object.getDateRegistration()))
                .dateClose(LocalDateFormatter.format(object.getDateClose()))
                .status(Status.valueOf(object.getStatus()))
                .total(object.getTotal())
                .build();
    }

    public static OrderCreateMapper getInstance(){
        return INSTANCE;
    }
}
