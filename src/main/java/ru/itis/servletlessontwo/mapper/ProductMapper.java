package ru.itis.servletlessontwo.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.servletlessontwo.dto.request.NewProductRequest;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.model.ProductEntity;

import java.util.List;

public interface ProductMapper extends RowMapper<ProductEntity> {
    ProductEntity toEntity(NewProductRequest request);
    ListProductsResponse toDto(List<ProductEntity> entity);
}
