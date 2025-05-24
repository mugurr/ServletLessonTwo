package ru.itis.servletlessontwo.mapper.impl;

import ru.itis.servletlessontwo.dto.request.NewProductRequest;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.dto.response.ProductResponse;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.utils.ImageUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductEntity toEntity(NewProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .image(request.getImage())
                .build();
    }

    @Override
    public ListProductsResponse toDto(List<ProductEntity> entity) {
        List<ProductResponse> productResponses = entity.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .category(product.getCategories())
                        .image(ImageUtils.encodeToBase64(product.getImage()))
                        .build())
                .collect(Collectors.toList());

        return ListProductsResponse.builder()
                .products(productResponses)
                .build();
    }

    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .quantity(rs.getInt("quantity"))
                .image(rs.getBytes("image"))
                .build();
    }
}
