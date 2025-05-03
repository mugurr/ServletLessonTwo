package ru.itis.servletlessontwo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.servletlessontwo.service.Calculator;
import ru.itis.servletlessontwo.service.impl.CalculatorImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calculator", value = "/calculator")
public class CalculatorServlet extends HttpServlet {
    private Calculator calculator;

    public void init() {
        calculator = new CalculatorImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = Integer.parseInt(req.getParameter("value1"));
        int b = Integer.parseInt(req.getParameter("value2"));
        String operator = req.getParameter("operation1");

        //int result = calculator.sum(a,b);
        int result = 0;

        switch (operator) {
            case "+":
                result = calculator.sum(a, b);
                break;
            case "-":
                result = calculator.minus(a, b);
                break;
            case "*":
                result = calculator.multiply(a, b);
                break;
            case "/":
                result = calculator.divide(a, b);
                break;
        }

        PrintWriter out = resp.getWriter();

        out.println("<html><body");
        out.println("<h1> Result: " + result + "</h1>");
        out.println("<h1> Operation: " + operator + "</h1>");
        out.println("</body></html>");


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
