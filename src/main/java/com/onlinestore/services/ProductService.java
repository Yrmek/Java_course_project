package com.onlinestore.services;

import com.onlinestore.ViewModels.ProductView;
import com.onlinestore.dao.ProductDAO;
import com.onlinestore.dao.ProductDAOImpl;
import com.onlinestore.entities.Product;

import java.util.List;
import java.util.ArrayList;

public class ProductService {
    private static ProductService instance;
    private final ProductDAO productDAO = new ProductDAOImpl();

    private ProductService() {}
    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public List<ProductView> getAllProductViews() {
        List<ProductView> views = new ArrayList<>();
        for (Product p : productDAO.findAll()) {
            views.add(new ProductView(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock()));
        }
        return views;
    }

    public ProductView getProductViewById(int id) {
        return productDAO.findById(id)
            .map(p -> new ProductView(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock()))
            .orElse(null);
    }

    public void addProduct(Product product) {
        productDAO.create(product);
    }

    public Product getProductById(int id) {
        return productDAO.findById(id).orElse(null);
    }
} 