import com.onlinestore.entities.OrderView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<OrderView> getAllOrders() throws SQLException {
        List<OrderView> orders = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT o.id, u.email, o.created_at, o.total_price, o.is_paid, u.id as user_id " +
                     "FROM orders o JOIN users u ON o.user_id = u.id")) {
            while (rs.next()) {
                orders.add(new OrderView(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at"),
                    rs.getDouble("total_price"),
                    rs.getBoolean("is_paid"),
                    rs.getInt("user_id")
                ));
            }
        }
        return orders;
    }
} 