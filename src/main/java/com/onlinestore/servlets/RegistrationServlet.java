package com.onlinestore.servlets;


import java.io.IOException;

import com.onlinestore.services.UserService;
import com.onlinestore.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.existsByEmail(email)) {
            request.setAttribute("error", "Пользователь с таким email уже существует");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(email, password, "ROLE_USER");
        userService.createUser(newUser);

        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        response.sendRedirect(request.getContextPath() + "/products");
    }
}