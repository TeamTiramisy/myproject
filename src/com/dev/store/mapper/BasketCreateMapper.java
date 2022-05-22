package com.dev.store.mapper;

import com.dev.store.dto.BasketCreateDto;
import com.dev.store.entity.Basket;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketCreateMapper implements Mapper<BasketCreateDto, Basket>{

    private static final BasketCreateMapper INSTANCE = new BasketCreateMapper();
    @Override
    public Basket map(BasketCreateDto object) {
        return Basket.builder()
                .users_id(object.getUsers_id())
                .technic_id(object.getTechnic_id())
                .build();
    }

    public static BasketCreateMapper getInstance(){
        return INSTANCE;
    }
}
