package ru.itis.servletlessontwo.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    Long id;
    String name;
    String description;
    double price;
    int quantity;
    byte[] image;
}
