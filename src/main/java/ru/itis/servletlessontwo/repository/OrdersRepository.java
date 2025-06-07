package ru.itis.servletlessontwo.repository;

import ru.itis.servletlessontwo.model.OrdersEntity;

import java.util.List;

public interface OrdersRepository {
    void save(OrdersEntity order);
    List<OrdersEntity> findAllByUserId(Long userId);
    void updateStatus(Long orderId, String statusCode);
    void delete(Long orderId);
}
