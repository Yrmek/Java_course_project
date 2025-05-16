package com.onlinestore.servlets;

import com.onlinestore.utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/admin/update-order")
public class UpdateOrderStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        boolean paid = Boolean.parseBoolean(request.getParameter("paid"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Обновляем статус оплаты
            try (PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET is_paid = ? WHERE id = ?")) {
                stmt.setBoolean(1, paid);
                stmt.setInt(2, orderId);
                stmt.executeUpdate();
            }

            // Если не оплачен — блокируем пользователя
            if (!paid) {
                try (PreparedStatement stmt = conn.prepareStatement("UPDATE users SET is_blocked = 1 WHERE id = ?")) {
                    stmt.setInt(1, userId);
                    stmt.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            throw new ServletException("Ошибка обновления заказа", e);
        }

        response.sendRedirect("orders");
    }
}
