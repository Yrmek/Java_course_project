package com.onlinestore.servlets;

import com.onlinestore.utils.DatabaseConnection;
import com.onlinestore.entities.User;
import com.onlinestore.services.OrderService;
import com.onlinestore.entities.OrderView;

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
        try {
            List<OrderView> orders = OrderService.getInstance().getAllOrders();
            request.setAttribute("orders", orders);
        } catch (Exception e) {
            throw new ServletException("Ошибка загрузки заказов", e);
        }
        request.getRequestDispatcher("/WEB-INF/views/admin/manage-orders.jsp").forward(request, response);
    }
}
