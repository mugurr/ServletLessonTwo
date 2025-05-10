package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.dto.response.ListCategoriesResponse;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;

public interface CategoryService {
    ListCategoriesResponse getAllCategories();
}
