package ru.itis.servletlessontwo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itis.servletlessontwo.model.OrdersEntity;
import ru.itis.servletlessontwo.repository.OrdersRepository;
import ru.itis.servletlessontwo.service.OrdersService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public void createOrder(OrdersEntity order) {
        ordersRepository.save(order);
    }

    @Override
    public List<OrdersEntity> getOrdersByUserId(Long userId) {
        return ordersRepository.findAllByUserId(userId);
    }

    @Override
    public void updateOrdersStatus(Long orderId, String statusCode) {
        ordersRepository.updateStatus(orderId, statusCode);
    }

    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.delete(orderId);
    }
}
