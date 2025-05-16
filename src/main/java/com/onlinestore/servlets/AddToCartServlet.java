package com.onlinestore.servlets;

import com.onlinestore.dao.ProductDAO;
import com.onlinestore.dao.ProductDAOImpl;
import com.onlinestore.entities.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product product = productDAO.findById(productId).orElse(null);
        if (product == null) {
            response.sendRedirect("products");
            return;
        }

        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        session.setAttribute("cart", cart);

        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("products");
        }
    }
}
