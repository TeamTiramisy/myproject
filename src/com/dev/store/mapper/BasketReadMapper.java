package com.dev.store.mapper;

import com.dev.store.dto.BasketReadDto;
import com.dev.store.entity.Basket;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketReadMapper implements Mapper<Basket, BasketReadDto> {

    private static final BasketReadMapper INSTANCE = new BasketReadMapper();

    @Override
    public BasketReadDto map(Basket object) {
        return BasketReadDto.builder()
                .id(object.getId())
                .users_id(object.getUsers_id())
                .technic_id(object.getTechnic_id())
                .build();
    }

    public static BasketReadMapper getInstance(){
        return INSTANCE;
    }
}
