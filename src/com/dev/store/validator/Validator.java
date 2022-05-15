package com.dev.store.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);

}
