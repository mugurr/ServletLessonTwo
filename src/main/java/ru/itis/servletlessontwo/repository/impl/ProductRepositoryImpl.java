package ru.itis.servletlessontwo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.servletlessontwo.dto.request.CategoryRequest;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.mapper.impl.ProductMapperImpl;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.repository.CategoryRepository;
import ru.itis.servletlessontwo.repository.ProductRepository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";

    private static final String SQL_SELECT_ALL_PRODUCTS = "select * from products";

    private static final String SQL_INSERT_PRODUCT = "insert into products (name, description, price, image) values (?, ?, ?, ?)";

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, productMapper);

        for (ProductEntity product : products) {
            product.setCategories(categoryRepository.findCategoriesByProductId(product.getId()));
        }

        return products;
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        try {
            ProductEntity product = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, productMapper, id);

            if (product != null) {
                product.setCategories(categoryRepository.findCategoriesByProductId(id));
            }

            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductEntity> saveNewProduct(ProductEntity product, List<CategoryRequest> category) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_PRODUCT, new String[]{"id"});
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4,product.getQuantity());
                ps.setBytes(4, product.getImage());
                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();

            if (product.getCategories() == null) {
                categoryRepository.saveProductCategories(id, category);
            }

            return findProductById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
