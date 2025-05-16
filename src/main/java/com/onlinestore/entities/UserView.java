package com.onlinestore.entities;

public class UserView {
    private int id;
    private String email;
    private String role;
    private boolean blocked;

    public UserView(int id, String email, String role, boolean blocked) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.blocked = blocked;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public boolean isBlocked() { return blocked; }
} 