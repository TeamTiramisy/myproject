package com.dev.store.mapper;

import com.dev.store.dto.TechnicCreateDto;
import com.dev.store.entity.Category;
import com.dev.store.entity.Technic;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicCreateMapper implements Mapper<TechnicCreateDto, Technic>{

    private static final TechnicCreateMapper INSTANCE = new TechnicCreateMapper();

    @Override
    public Technic map(TechnicCreateDto object) {
        return Technic.builder()
                .name(object.getName())
                .category(Category.valueOf(object.getCategory()))
                .description(object.getDescription())
                .price(Integer.valueOf(object.getPrice()))
                .amount(Integer.valueOf(object.getAmount()))
                .image(object.getCategory() + "/" + object.getImage().getSubmittedFileName())
                .build();
    }

    public static TechnicCreateMapper getInstance(){
        return INSTANCE;
    }
}
