package com.dev.store.validator;

import com.dev.store.dto.TechnicCreateDto;
import com.dev.store.dto.UserCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddProductValidator implements Validator<TechnicCreateDto>{

    private static final AddProductValidator INSTANCE = new AddProductValidator();

    @Override
    public ValidationResult isValid(TechnicCreateDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getName().isEmpty()){
            validationResult.add(Error.of("invalid.name", "Укажите название товара", "Specify the name of the product"));
        }
        if (object.getCategory().isEmpty()){
            validationResult.add(Error.of("invalid.category", "Укажите категорию товара", "Specify the product category"));
        }
        if (object.getDescription().isEmpty()){
            validationResult.add(Error.of("invalid.description", "Добавте описание товара", "Add product description"));
        }
        if (object.getPrice().isEmpty()){
            validationResult.add(Error.of("invalid.price", "Укажите цену товара", "Specify the price of the item"));
        }
        if (object.getAmount().isEmpty()){
            validationResult.add(Error.of("invalid.amount", "Укажите количество товара", "Specify the quantity of product"));
        }

        return validationResult;
    }

    public static AddProductValidator getInstance(){
        return INSTANCE;
    }
}
