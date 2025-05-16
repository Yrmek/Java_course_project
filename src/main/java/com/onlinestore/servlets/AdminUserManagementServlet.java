package com.onlinestore.servlets;

import com.onlinestore.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user-status")
public class AdminUserManagementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        boolean block = Boolean.parseBoolean(request.getParameter("block"));

        com.onlinestore.entities.User user = UserService.getInstance().getUserById(userId);
        if (user != null) {
            user.setBlocked(block);
            UserService.getInstance().updateUser(user);
        }

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
