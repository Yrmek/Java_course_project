package com.onlinestore.entities;

public class User {
    private int id;
    private String email;
    private String password;
    private String role;
    private boolean isBlocked;

    // Конструктор по умолчанию
    public User() {}

    // Конструктор с параметрами
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isBlocked = false;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isBlocked() { return isBlocked; }
    public void setBlocked(boolean blocked) { isBlocked = blocked; }

    // Дополнительные методы
    public boolean isAdmin() {
        return "ROLE_ADMIN".equalsIgnoreCase(role);
    }

    public void promoteToAdmin() {
        this.role = "ROLE_ADMIN";
    }

    public void demoteToUser() {
        this.role = "ROLE_USER";
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return 31 * id + email.hashCode();
    }
}