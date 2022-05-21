package com.dev.store.service;

import com.dev.store.DAO.UserDao;
import com.dev.store.dto.UserCreateDto;
import com.dev.store.dto.UserReadDto;
import com.dev.store.entity.BlackList;
import com.dev.store.entity.Role;
import com.dev.store.entity.User;
import com.dev.store.exception.ValidationException;
import com.dev.store.mapper.UserCreateMapper;
import com.dev.store.mapper.UserReadMapper;
import com.dev.store.validator.CreateUserValidator;
import com.dev.store.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator validator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final UserCreateMapper mapper = UserCreateMapper.getInstance();
    private final UserReadMapper readMapper = UserReadMapper.getInstance();

    public List<UserReadDto> findAll(){
        return userDao.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<UserReadDto> findById(Long id){
        return userDao.findById(id)
                .map(readMapper::map);
    }

    public Optional<UserReadDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .map(readMapper::map);
    }

    public void update(Long id, UserCreateDto userCreateDto){
        ValidationResult valid = validator.isValid(userCreateDto);
        if (!valid.isValid()){
            throw new ValidationException(valid.getErrors());
        }
        User user = mapper.map(userCreateDto);
        user.setId(id);
        userDao.update(user);
    }

    public void updateRoleAndBlacklist(Long id, UserCreateDto userCreateDto){
        User user = userDao.findById(id).orElseThrow();
        user.setRole(Role.valueOf(userCreateDto.getRole()));
        user.setBlackList(BlackList.valueOf(userCreateDto.getBlackList()));

        userDao.update(user);

    }

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
