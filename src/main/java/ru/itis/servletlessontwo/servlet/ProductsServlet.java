package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.service.ProductService;

import java.io.IOException;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        productService = (ProductService) servletContext.getAttribute("productService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ListProductsResponse listProductsResponse = productService.getAllProducts();

        session.setAttribute("products", listProductsResponse);

        req.getRequestDispatcher("jsp/productsWithoutCategoryTable.jsp").forward(req, resp);

    }

}
