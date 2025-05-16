package com.onlinestore.services;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.User;
import com.onlinestore.utils.DatabaseConnection;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.Map;

public class CheckoutService {
    private static CheckoutService instance;
    private CheckoutService() {}
    public static synchronized CheckoutService getInstance() {
        if (instance == null) {
            instance = new CheckoutService();
        }
        return instance;
    }

    public void checkout(HttpSession session, Map<Product, Integer> cart, User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            double total = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                total += entry.getKey().getPrice() * entry.getValue();
            }
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
            conn.commit();
            session.removeAttribute("cart");
        }
    }
} 