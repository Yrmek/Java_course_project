package com.onlinestore.servlets;

import com.onlinestore.ViewModels.ProductView;
import com.onlinestore.entities.Product;
import com.onlinestore.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendRedirect("products.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            ProductView product = ProductService.getInstance().getProductViewById(id);
            if (product != null) {
                request.setAttribute("product", product);
            }
        } catch (NumberFormatException e) {
            // Некорректный ID — игнорируем
        }

        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }
}
