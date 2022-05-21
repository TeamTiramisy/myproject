package com.dev.store.mapper;

import com.dev.store.dto.UserReadDto;
import com.dev.store.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final static UserReadMapper INSTANCE = new UserReadMapper();

    @Override
    public UserReadDto map(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .email(object.getEmail())
                .password(object.getPassword())
                .tel(object.getTel())
                .address(object.getAddress())
                .role(object.getRole().name())
                .gender(object.getGender().name())
                .blackList(object.getBlackList().name())
                .build();
    }

    public static UserReadMapper getInstance() {
        return INSTANCE;
    }
}
