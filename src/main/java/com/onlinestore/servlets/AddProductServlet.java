package com.onlinestore.servlets;

import com.onlinestore.dao.ProductDAO;
import com.onlinestore.dao.ProductDAOImpl;
import com.onlinestore.entities.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/add-product")
public class AddProductServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/add-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);

        productDAO.create(product);

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
