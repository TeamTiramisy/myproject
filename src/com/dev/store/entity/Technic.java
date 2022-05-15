package com.dev.store.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technic {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private Integer price;
    private Integer amount;
    private String image;
}
