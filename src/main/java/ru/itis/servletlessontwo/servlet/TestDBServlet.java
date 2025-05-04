package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

@WebServlet("/test-db")
public class TestDBServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JdbcTemplate jt = (JdbcTemplate) req.getServletContext().getAttribute("jdbcTemplate");
            String result = jt.queryForObject("SELECT 'DB connection OK'", String.class);
            resp.getWriter().println(result);
        } catch (Exception e) {
            resp.getWriter().println("DB connection FAILED: " + e.getMessage());
        }
    }
}
