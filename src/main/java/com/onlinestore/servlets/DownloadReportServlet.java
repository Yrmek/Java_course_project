package com.onlinestore.servlets;

import com.onlinestore.utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/admin/report")
public class DownloadReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=orders_report.csv");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT o.id, u.email, o.created_at, o.total_price, o.is_paid " +
                     "FROM orders o JOIN users u ON o.user_id = u.id");
             PrintWriter out = response.getWriter()) {

            out.println("Order ID,User Email,Date,Total Price,Paid");

            while (rs.next()) {
                int orderId = rs.getInt("id");
                String email = rs.getString("email");
                Timestamp date = rs.getTimestamp("created_at");
                double total = rs.getDouble("total_price");
                boolean paid = rs.getBoolean("is_paid");

                out.printf("%d,%s,%s,%.2f,%s%n", orderId, email, date.toString(), total, paid ? "Yes" : "No");
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка при формировании отчёта", e);
        }
    }
}
