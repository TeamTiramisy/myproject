package com.dev.store.service;

import com.dev.store.DAO.UserDao;
import com.dev.store.dto.UserCreateDto;
import com.dev.store.entity.User;
import com.dev.store.exception.ValidationException;
import com.dev.store.mapper.UserCreateMapper;
import com.dev.store.validator.CreateUserValidator;
import com.dev.store.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator validator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final UserCreateMapper mapper = UserCreateMapper.getInstance();


    public Long create(UserCreateDto userDto){
        ValidationResult valid = validator.isValid(userDto);
        if (!valid.isValid()){
            throw new ValidationException(valid.getErrors());
        }
        User user = mapper.map(userDto);
        userDao.save(user);
        return user.getId();
    }

    public static UserService getInstance(){
        return INSTANCE;
    }
}
