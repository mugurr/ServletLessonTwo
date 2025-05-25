package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.dto.response.ListProductsResponse;

public interface FavoritesService {

    ListProductsResponse getAllFavorites(Long userId);

    void deleteFavorite(Long userId, Long productId);

    void addFavorite(Long userId, Long productId);

}
