package ru.itis.servletlessontwo.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.servletlessontwo.dto.response.ListCategoriesResponse;
import ru.itis.servletlessontwo.model.CategoryEntity;

import java.util.List;

public interface CategoryMapper extends RowMapper<CategoryEntity> {
    ListCategoriesResponse toDto(List<CategoryEntity> entity);
}
