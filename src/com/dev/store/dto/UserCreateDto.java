package com.dev.store.dto;

import com.dev.store.entity.Gender;
import com.dev.store.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateDto {

    String firstname;
    String lastname;
    String email;
    String password;
    String tel;
    String address;
    String role;
    String gender;
}
