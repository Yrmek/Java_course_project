package com.onlinestore.servlets;

import com.onlinestore.entities.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}
