package com.dev.store.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    String messageRu;
    String messageEn;
}
