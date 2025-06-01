package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.service.FavoritesService;

import java.io.IOException;

@WebServlet("/toggleProduct")
public class ToggleProductServlet extends HttpServlet {

    private FavoritesService favoritesService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        favoritesService = (FavoritesService) context.getAttribute("favoritesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.parseLong(req.getParameter("productId"));
        boolean isFavorite = Boolean.parseBoolean(req.getParameter("isFavorite"));

        HttpSession session = req.getSession(true);
        UserDataResponse user = (UserDataResponse) session.getAttribute("user");

        Long userId = user.getId();

        if (!isFavorite) {
            favoritesService.addFavorite(userId, productId);
        } else {
            favoritesService.deleteFavorite(userId, productId);
        }

        resp.sendRedirect("/products");
    }
}
