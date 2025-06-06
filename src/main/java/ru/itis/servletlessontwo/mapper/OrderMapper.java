package ru.itis.servletlessontwo.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.servletlessontwo.dto.request.OrderRequest;
import ru.itis.servletlessontwo.model.OrdersEntity;

public interface OrderMapper extends RowMapper<OrdersEntity> {

    OrdersEntity toEntity(OrderRequest request);
}
