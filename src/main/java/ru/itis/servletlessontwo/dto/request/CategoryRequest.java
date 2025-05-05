package ru.itis.servletlessontwo.dto.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private String name;
}
