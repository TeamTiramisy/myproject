package com.dev.store.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TechnicReadDto {

    Long id;
    String name;
    String category;
    String description;
    Integer price;
    Integer amount;
    String image;
}
