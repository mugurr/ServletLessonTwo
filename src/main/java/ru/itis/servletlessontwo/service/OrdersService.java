package ru.itis.servletlessontwo.service;

import ru.itis.servletlessontwo.model.OrdersEntity;

import java.util.List;

public interface OrdersService {

    void createOrder(OrdersEntity order);

    List<OrdersEntity> getOrdersByUserId(Long userId);

    void updateOrdersStatus(Long orderId, String statusCode);

    void deleteOrder(Long orderId);
}
