package com.onlinestore.services;

import com.onlinestore.utils.DatabaseConnection;
import java.io.PrintWriter;
import java.sql.*;

public class ReportService {
    private static ReportService instance;
    private ReportService() {}
    public static synchronized ReportService getInstance() {
        if (instance == null) {
            instance = new ReportService();
        }
        return instance;
    }

    public void writeOrdersReport(PrintWriter out) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT o.id, u.email, o.created_at, o.total_price, o.is_paid " +
                     "FROM orders o JOIN users u ON o.user_id = u.id")) {
            out.println("Order ID,User Email,Date,Total Price,Paid");
            while (rs.next()) {
                int orderId = rs.getInt("id");
                String email = rs.getString("email");
                Timestamp date = rs.getTimestamp("created_at");
                double total = rs.getDouble("total_price");
                boolean paid = rs.getBoolean("is_paid");
                out.printf("%d,%s,%s,%.2f,%s%n", orderId, email, date.toString(), total, paid ? "Yes" : "No");
            }
        }
    }
} 