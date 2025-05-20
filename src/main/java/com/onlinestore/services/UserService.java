package com.onlinestore.services;

import com.onlinestore.ViewModels.UserView;
import com.onlinestore.dao.UserDAO;
import com.onlinestore.dao.UserDAOImpl;
import com.onlinestore.entities.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private final UserDAO userDAO = new UserDAOImpl();

    private UserService() {}

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User authenticate(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && !user.isBlocked() && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    public List<UserView> getAllUserViews() {
        List<UserView> views = new java.util.ArrayList<>();
        for (User user : userDAO.findAll()) {
            views.add(new UserView(user.getId(), user.getEmail(), user.getRole(), user.isBlocked()));
        }
        return views;
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    public void createUser(User user) {
        userDAO.create(user);
    }
}