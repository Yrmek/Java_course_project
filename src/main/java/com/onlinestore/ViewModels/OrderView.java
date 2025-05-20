package com.onlinestore.ViewModels;

import java.sql.Timestamp;

public class OrderView {
    private int id;
    private String email;
    private Timestamp createdAt;
    private double totalPrice;
    private boolean paid;
    private int userId;

    public OrderView(int id, String email, Timestamp createdAt, double totalPrice, boolean paid, int userId) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.userId = userId;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public Timestamp getCreatedAt() { return createdAt; }
    public double getTotalPrice() { return totalPrice; }
    public boolean isPaid() { return paid; }
    public int getUserId() { return userId; }
} 