package ru.itis.servletlessontwo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itis.servletlessontwo.dto.request.CategoryRequest;
import ru.itis.servletlessontwo.dto.request.NewProductRequest;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.model.ProductEntity;
import ru.itis.servletlessontwo.repository.ProductRepository;
import ru.itis.servletlessontwo.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ListProductsResponse getAllProducts() {

        List<ProductEntity> products = productRepository.getAllProducts();
        log.info("Get all products");

        if (products.isEmpty()) {
            return new ListProductsResponse(Collections.emptyList());
        }
        return productMapper.toDto(products);
    }

    @Override
    public void saveNewProduct(NewProductRequest request, List<CategoryRequest> requestList) {
        Optional<ProductEntity> optionalProduct = productRepository.saveNewProduct(productMapper.toEntity(request), requestList);

    }
}
