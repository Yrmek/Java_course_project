package com.onlinestore.servlets;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.ProductView;
import com.onlinestore.services.ProductService;

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
        Map<ProductView, Integer> cartView = new HashMap<>();
        if (cart != null) {
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                ProductView pv = ProductService.getInstance().getProductViewById(entry.getKey().getId());
                if (pv != null) {
                    cartView.put(pv, entry.getValue());
                }
            }
        }
        request.setAttribute("cart", cartView);
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}
