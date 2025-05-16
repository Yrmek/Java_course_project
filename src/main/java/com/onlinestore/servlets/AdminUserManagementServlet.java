package com.onlinestore.servlets;

import com.onlinestore.dao.UserDAO;
import com.onlinestore.dao.UserDAOImpl;
import com.onlinestore.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user-status")
public class AdminUserManagementServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        boolean block = Boolean.parseBoolean(request.getParameter("block"));

        User user = userDAO.findById(userId);
        if (user != null) {
            user.setBlocked(block);
            userDAO.update(user);
        }

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
