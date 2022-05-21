package com.dev.store.mapper;

import com.dev.store.dto.UserCreateDto;
import com.dev.store.entity.BlackList;
import com.dev.store.entity.Gender;
import com.dev.store.entity.Role;
import com.dev.store.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateMapper implements Mapper<UserCreateDto, User>{

    private static final UserCreateMapper INSTANCE = new UserCreateMapper();

    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .email(object.getEmail())
                .password(object.getPassword())
                .tel(object.getTel())
                .address(object.getAddress())
                .role(Role.valueOf(object.getRole()))
                .gender(Gender.valueOf(object.getGender()))
                .blackList(BlackList.valueOf(object.getBlackList()))
                .build();
    }

    public static UserCreateMapper getInstance(){
        return INSTANCE;
    }
}
