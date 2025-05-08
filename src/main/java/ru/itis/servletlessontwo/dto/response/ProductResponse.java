package ru.itis.servletlessontwo.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String description;

    private double price;

    private int quantity;

    private String image;
}
