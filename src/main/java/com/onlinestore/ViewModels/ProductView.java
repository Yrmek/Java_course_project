package com.onlinestore.ViewModels;

public class ProductView {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public ProductView(int id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
} 