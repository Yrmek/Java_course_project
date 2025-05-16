package com.onlinestore.dao;

import com.onlinestore.entities.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    void create(Product product);
    Optional<Product> findById(int id);
    void update(Product product);
    void delete(int id);
    List<Product> findAll();
}