package ru.itis.servletlessontwo.dto.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewProductRequest {
    private String name;

    private String description;

    private double price;

    private int quantity;

    private byte[] image;
}
