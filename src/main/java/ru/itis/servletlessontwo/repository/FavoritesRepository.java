package ru.itis.servletlessontwo.repository;

import ru.itis.servletlessontwo.model.ProductEntity;

import java.util.List;

public interface FavoritesRepository {

    void addToFavourites(Long userId, Long productId);

    void removeFromFavourites(Long userId, Long productId);

    List<ProductEntity> getFavouritesByUser(Long userId);

    boolean isProductInFavourites(Long userId, Long productId);
}
