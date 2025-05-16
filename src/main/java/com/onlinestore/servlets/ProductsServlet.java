package com.onlinestore.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.onlinestore.dao.ProductDAO;
import com.onlinestore.dao.ProductDAOImpl;
import com.onlinestore.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println(">>> ProductsServlet вызван");
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}