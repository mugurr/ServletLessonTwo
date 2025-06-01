package ru.itis.servletlessontwo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.repository.FavoritesRepository;
import ru.itis.servletlessontwo.service.FavoritesService;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesRepository favoritesRepository;

    private final ProductMapper productMapper;

    @Override
    public ListProductsResponse getAllFavorites(Long userId) {
        List<ProductEntity> products = favoritesRepository.getFavoritesByUser(userId);
        if (products.isEmpty()) {
            return new ListProductsResponse(Collections.emptyList());
        }
        return productMapper.toDto(products);
    }

    @Override
    public void deleteFavorite(Long userId, Long productId) {
        favoritesRepository.removeFromFavorites(userId, productId);
    }

    @Override
    public void addFavorite(Long userId, Long productId) {
        favoritesRepository.addToFavorites(userId, productId);
    }
}
