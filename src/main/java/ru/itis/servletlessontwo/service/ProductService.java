package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.dto.request.CategoryRequest;
import ru.itis.servletlessontwo.dto.request.NewProductRequest;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;

import java.util.List;

public interface ProductService {
    ListProductsResponse getAllProducts();

    void saveNewProduct(NewProductRequest request, List<CategoryRequest> requestList);
}
