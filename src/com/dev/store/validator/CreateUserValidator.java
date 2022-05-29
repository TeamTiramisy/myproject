package com.dev.store.validator;

import com.dev.store.dto.UserCreateDto;
import com.dev.store.entity.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<UserCreateDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(UserCreateDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getFirstname().isEmpty()){
            validationResult.add(Error.of("invalid.firstname", "Введите имя", "Enter your name"));
        }
        if (object.getLastname().isEmpty()){
            validationResult.add(Error.of("invalid.lastname", "Введите фамилию", "Enter last name"));
        }
        if (object.getEmail().isEmpty()){
            validationResult.add(Error.of("invalid.email", "Введите эл почту", "Enter email"));
        }
        if (object.getPassword().isEmpty()){
            validationResult.add(Error.of("invalid.password", "Введите пароль", "Enter password"));
        }
        if (object.getTel().isEmpty()){
            validationResult.add(Error.of("invalid.tel", "Введите телефон", "Enter phone"));
        }
        if (object.getAddress().isEmpty()){
            validationResult.add(Error.of("invalid.address", "Введите адресс", "Enter address"));
        }
        if (object.getGender() == null || Gender.valueOf(object.getGender()) == null){
            validationResult.add(Error.of("invalid.gender", "Введите пол", "Enter gender"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}
