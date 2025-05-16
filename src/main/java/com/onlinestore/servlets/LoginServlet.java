package com.onlinestore.servlets;

import java.io.IOException;

import com.onlinestore.entities.User;
import com.onlinestore.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = UserService.getInstance().authenticate(email, password);
        System.out.println(">>> Найдено товаров: ");
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("products");
            }
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        }
    }
}