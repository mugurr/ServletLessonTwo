package ru.itis.servletlessontwo.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.repository.CategoryRepository;
import ru.itis.servletlessontwo.repository.FavoritesRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FavoritesRepositoryImpl implements FavoritesRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String INSERT_INTO_FAVORITE = "insert into favorites (user_id, product_id) values (?, ?)";

    private final static String SELECT_FROM_FAVORITE = "select * from favorites";

    private final static String DELETE_FROM_FAVORITE = "delete from favorites WHERE user_id = ? and product_id = ?";

    private final static String SELECT_FROM_FAVORITE_BY_ID_USER = "select p.id, p.name, p.description, p.price, p.quantity, p.image " +
            "from products p " +
            "join favorites f ON p.id = f.product_id " +
            "where f.user_id = ?";

    private final static String SELECT_IS_PRODUCT_IN_FAVORITE = "select count(*) from favorites where user_id = ? and product_id = ?";

    private final ProductMapper productMapper ;

    private final CategoryRepository categoryRepository;

    @Override
    public void addToFavorites(Long userId, Long productId) {
        if (!isProductInFavorites(userId, productId)) {
            jdbcTemplate.update(INSERT_INTO_FAVORITE, userId, productId);
        }
    }

    @Override
    public void removeFromFavorites(Long userId, Long productId) {
        jdbcTemplate.update(DELETE_FROM_FAVORITE, userId, productId);
    }

    @Override
    public List<ProductEntity> getFavoritesByUser(Long userId) {
        List<ProductEntity> products = jdbcTemplate.query(SELECT_FROM_FAVORITE_BY_ID_USER, productMapper, userId);
        for (ProductEntity product : products) {
            product.setCategories(categoryRepository.findCategoriesByProductId(product.getId()));
            product.setFavorite(isProductInFavorites(userId, product.getId()));
        }
        return products;
    }

    @Override
    public boolean isProductInFavorites(Long userId, Long productId) {
        Integer count = jdbcTemplate.queryForObject(SELECT_IS_PRODUCT_IN_FAVORITE, new Object[]{userId, productId}, Integer.class);
        return count > 0;
    }
}
