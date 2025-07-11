package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.dto.response.ProductResponse;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.model.OrdersEntity;
import ru.itis.servletlessontwo.service.OrdersService;
import ru.itis.servletlessontwo.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    private OrdersService ordersService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ordersService = (OrdersService) servletContext.getAttribute("ordersService");
        productService = (ProductService) servletContext.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        UserDataResponse user = (UserDataResponse) session.getAttribute("user");

        List<OrdersEntity> ordersEntityList = ordersService.getOrdersByUserId(user.getId());
        ListProductsResponse listProductsResponse = productService.getAllProducts(user.getId());

        // Преобразуем список в Map (ID продукта -> продукт)
        Map<Long, ProductResponse> productMap = listProductsResponse.getProducts().stream()
                .collect(Collectors.toMap(ProductResponse::getId, Function.identity()));

        session.setAttribute("orders", ordersEntityList);
        session.setAttribute("productMap", productMap);

        req.getRequestDispatcher("/jsp/orders.jsp").forward(req, resp);
    }
}
