package ru.itis.servletlessontwo.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListProductsResponse {
    List<ProductResponse> products;
}
