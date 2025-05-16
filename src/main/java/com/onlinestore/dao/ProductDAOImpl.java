package com.onlinestore.dao;

import com.onlinestore.dao.ProductDAO;
import com.onlinestore.entities.Product;
import com.onlinestore.exeptions.DatabaseException;
import com.onlinestore.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products (name, description, price, stock) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при создании товара", e);
        }
    }

    @Override
    public Optional<Product> findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при поиске товара", e);
        }
        
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при обновлении товара", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при удалении товара", e);
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при получении списка товаров", e);
        }
        System.out.println(">>> Найдено товаров: " + products.size());
        return products;
    }
}