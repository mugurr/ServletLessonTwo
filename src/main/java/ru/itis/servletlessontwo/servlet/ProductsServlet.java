package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.response.ListCategoriesResponse;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.service.CategoryService;
import ru.itis.servletlessontwo.service.ProductService;

import java.io.IOException;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ProductService productService;

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();

        productService = (ProductService) servletContext.getAttribute("productService");
        categoryService = (CategoryService) servletContext.getAttribute("categoryService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ListProductsResponse listProductsResponse = productService.getAllProducts();
        ListCategoriesResponse listCategoryResponse = categoryService.getAllCategories();

        session.setAttribute("products", listProductsResponse);
        session.setAttribute("categories", listCategoryResponse);

        req.getRequestDispatcher("jsp/products.jsp").forward(req, resp);

    }

}
