package com.onlinestore.servlets;


import java.io.IOException;

import com.onlinestore.dao.UserDAO;
import com.onlinestore.dao.UserDAOImpl;
import com.onlinestore.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.existsByEmail(email)) {
            request.setAttribute("error", "Пользователь с таким email уже существует");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(email, password, "ROLE_USER");
        userDAO.create(newUser);

        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        response.sendRedirect(request.getContextPath() + "/products");
    }
}