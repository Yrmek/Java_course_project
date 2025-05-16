package com.onlinestore.servlets;

import com.onlinestore.utils.DatabaseConnection;
import com.onlinestore.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/admin/orders")
public class ManageOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, Object>> orders = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT o.id, u.email, o.created_at, o.total_price, o.is_paid, u.id as user_id " +
                     "FROM orders o JOIN users u ON o.user_id = u.id")) {

            while (rs.next()) {
                Map<String, Object> order = new HashMap<>();
                order.put("id", rs.getInt("id"));
                order.put("email", rs.getString("email"));
                order.put("createdAt", rs.getTimestamp("created_at"));
                order.put("totalPrice", rs.getDouble("total_price"));
                order.put("paid", rs.getBoolean("is_paid"));
                order.put("userId", rs.getInt("user_id"));
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка загрузки заказов", e);
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/admin/manage-orders.jsp").forward(request, response);
    }
}
