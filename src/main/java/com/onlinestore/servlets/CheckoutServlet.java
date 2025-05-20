package com.onlinestore.servlets;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.User;
import com.onlinestore.services.CheckoutService;
import com.onlinestore.utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final CheckoutService checkoutService = CheckoutService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        // Проверка на наличие пользователя и корзины
        if (cart == null || cart.isEmpty() || user == null) {
            response.sendRedirect("products");
            return;
        }

        try {
            checkoutService.checkout(session, cart, user);
            response.sendRedirect("order-success.jsp");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при оформлении заказа", e);
        }
    }
}
