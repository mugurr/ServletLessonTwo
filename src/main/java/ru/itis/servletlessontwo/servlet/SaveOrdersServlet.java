package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.request.OrderRequest;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.mapper.OrderMapper;
import ru.itis.servletlessontwo.service.OrdersService;

import java.io.IOException;
import java.time.LocalDateTime;

import static ru.itis.servletlessontwo.model.OrdersEntity.STATUS_PENDING;

@WebServlet("/saveOrder")
public class SaveOrdersServlet extends HttpServlet {

    private OrdersService ordersService;
    private OrderMapper orderMapper;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ordersService = (OrdersService) servletContext.getAttribute("ordersService");
        orderMapper = (OrderMapper) servletContext.getAttribute("orderMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));

        HttpSession session = req.getSession(true);
        UserDataResponse user = (UserDataResponse) session.getAttribute("user");

        Long userId = user.getId();

        OrderRequest orderRequest = OrderRequest.builder()
                .userId(userId)
                .productId(productId)
                .statusCode(STATUS_PENDING)
                .orderDate(LocalDateTime.now())
                .build();

        ordersService.createOrder(orderMapper.toEntity(orderRequest));

        resp.sendRedirect("/orders");
    }
}
