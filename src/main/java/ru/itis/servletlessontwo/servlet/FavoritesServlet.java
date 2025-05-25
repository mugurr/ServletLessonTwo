package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.servletlessontwo.dto.response.ListProductsResponse;
import ru.itis.servletlessontwo.dto.response.UserDataResponse;
import ru.itis.servletlessontwo.service.FavoritesService;

import java.io.IOException;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {

    private FavoritesService favoritesService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        favoritesService = (FavoritesService) context.getAttribute("favoritesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        UserDataResponse user = (UserDataResponse) session.getAttribute("user");

        ListProductsResponse listProductsResponse = favoritesService.getAllFavorites(user.getId());

        session.setAttribute("favorites", listProductsResponse);

        req.getRequestDispatcher("jsp/favorites.jsp").forward(req, resp);
    }
}
