package com.dev.store.mapper;

import com.dev.store.dto.TechnicReadDto;
import com.dev.store.entity.Technic;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicReadMapper implements Mapper<Technic, TechnicReadDto>{

    public static final TechnicReadMapper INSTANCE = new TechnicReadMapper();

    @Override
    public TechnicReadDto map(Technic object) {
        return TechnicReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .category(object.getCategory().name())
                .description(object.getDescription())
                .price(object.getPrice())
                .amount(object.getAmount())
                .image(object.getImage())
                .build();
    }

    public static TechnicReadMapper getInstance(){
        return INSTANCE;
    }
}
