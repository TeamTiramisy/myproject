package com.dev.store.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TechnicCreateDto {

    String name;
    String category;
    String description;
    String price;
    String amount;
    Part image;
}
