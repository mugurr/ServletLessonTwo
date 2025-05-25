package ru.itis.servletlessontwo.repository;

import ru.itis.servletlessontwo.model.ProductEntity;

import java.util.List;

public interface FavoritesRepository {

    void addToFavorites(Long userId, Long productId);

    void removeFromFavorites(Long userId, Long productId);

    List<ProductEntity> getFavoritesByUser(Long userId);

    boolean isProductInFavorites(Long userId, Long productId);
}
