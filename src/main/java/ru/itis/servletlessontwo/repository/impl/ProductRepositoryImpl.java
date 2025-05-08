package ru.itis.servletlessontwo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.servletlessontwo.dto.request.CategoryRequest;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";

    private static final String SQL_SELECT_ALL_PRODUCTS = "select * from products";

    private static final String SQL_INSERT_PRODUCT = "insert into products (name, description, price, image) values (?, ?, ?, ?)";



    @Override
    public List<ProductEntity> getAllProducts() {
        return List.of();
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> saveNewProduct(ProductEntity product, List<CategoryRequest> category) {
        return Optional.empty();
    }
}
