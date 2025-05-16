package com.onlinestore.servlets;

import com.onlinestore.dao.ProductDAO;
import com.onlinestore.dao.ProductDAOImpl;
import com.onlinestore.entities.Product;
import com.onlinestore.entities.User;
import com.onlinestore.utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAOImpl();

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

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Подсчёт суммы заказа
            double total = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                total += entry.getKey().getPrice() * entry.getValue();
            }

            // 1. Создание записи в orders
            String insertOrderSql = "INSERT INTO orders (user_id, created_at, total_price) VALUES (?, GETDATE(), ?)";
            int orderId;

            try (PreparedStatement stmt = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, user.getId());
                stmt.setDouble(2, total);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        throw new SQLException("Не удалось получить ID нового заказа");
                    }
                }
            }

            // 2. Вставка позиций заказа
            String insertItemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertItemSql)) {
                for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                    Product product = entry.getKey();
                    int qty = entry.getValue();

                    stmt.setInt(1, orderId);
                    stmt.setInt(2, product.getId());
                    stmt.setInt(3, qty);
                    stmt.setDouble(4, product.getPrice());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            conn.commit(); // Подтверждаем транзакцию

            // 3. Очистка корзины
            session.removeAttribute("cart");

            // 4. Перенаправление на страницу успеха
            response.sendRedirect("order-success.jsp");

        } catch (SQLException e) {
            throw new ServletException("Ошибка при оформлении заказа", e);
        }
    }
}
