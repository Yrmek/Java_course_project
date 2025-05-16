package com.onlinestore.dao;


import com.onlinestore.entities.User;
import com.onlinestore.exeptions.DaoException;
import com.onlinestore.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users (email, password, role, is_blocked) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isBlocked());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Создание пользователя не удалось");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Не удалось получить ID пользователя");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании пользователя", e);
        }

        return user;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setBlocked(rs.getBoolean("is_blocked"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске пользователя по ID", e);
        }

        return user;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET email = ?, password = ?, role = ?, is_blocked = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isBlocked());
            stmt.setInt(5, user.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении пользователя", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setBlocked(rs.getBoolean("is_blocked"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка пользователей", e);
        }

        return users;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setBlocked(rs.getBoolean("is_blocked"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске пользователя по email", e);
        }

        return user;
    }

	@Override
	public boolean existsByEmail(String email) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
}