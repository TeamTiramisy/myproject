package com.dev.store.validator;

import com.dev.store.dto.TechnicCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateProductValidator implements Validator<TechnicCreateDto>{

    private static final UpdateProductValidator INSTANCE = new UpdateProductValidator();

    @Override
    public ValidationResult isValid(TechnicCreateDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getName().isEmpty()){
            validationResult.add(Error.of("invalid.name", "Укажите название товара"));
        }
        if (object.getDescription().isEmpty()){
            validationResult.add(Error.of("invalid.description", "Добавте описание товара"));
        }
        if (object.getPrice().isEmpty()){
            validationResult.add(Error.of("invalid.price", "Укажите цену товара"));
        }
        if (object.getAmount().isEmpty()){
            validationResult.add(Error.of("invalid.amount", "Укажите количество товара"));
        }

        return validationResult;
    }

    public static UpdateProductValidator getInstance(){
        return INSTANCE;
    }
}
