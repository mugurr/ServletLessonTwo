package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.dto.response.ListCategoriesResponse;

public interface CategoryService {
    ListCategoriesResponse getAllCategories();
}
