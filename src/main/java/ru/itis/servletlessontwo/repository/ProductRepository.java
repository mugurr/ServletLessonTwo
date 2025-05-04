package ru.itis.servletlessontwo.repository;

import ru.itis.servletlessontwo.model.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductEntity> getAllProducts();
    Optional<ProductEntity> findProductById(Long id);
    Optional<ProductEntity> saveNewProduct(ProductEntity product, List<CategoryRequest> category);
}
