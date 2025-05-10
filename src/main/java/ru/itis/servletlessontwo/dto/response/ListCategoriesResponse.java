package ru.itis.servletlessontwo.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoriesResponse {
    List<CategoryResponse> categories;
}
