package webshop.order;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import webshop.product.Product;
import webshop.user.User;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    public OrderDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertOrder(int userId, LocalDate date) {
        Date dateOrder = Date.valueOf(date);
        jdbcTemplate.update("Insert into orders(user_id, order_date) values (?,?)", userId, dateOrder);
    }

    public long saveOrder(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO orders(user_id,order_date) VALUES(?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, user.getID());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            return stmt;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void saveOrderedItems(User user, long order_id) {
        for (Map.Entry<Product, Integer> itemsToOrder : user.getShoppingCart().entrySet()) {
            jdbcTemplate.update("Insert into ordered_items(order_id, product_id, pieces) values (?,?,?)", order_id, itemsToOrder.getKey().getID(), itemsToOrder.getValue());
        }
    }

    public void insertOrderedItems(int orderId, int productId) {
        jdbcTemplate.update("Insert into ordered_items(order_id, product_id) values (?,?)", orderId, productId);
    }

    public Order findOrderByUserId(int userId) {
        return jdbcTemplate.queryForObject("Select * from orders where user_id = ?", (rs, rowNum)
                -> new Order(rs.getLong("id"), rs.getLong("user_id"), rs.getDate("order_date").toLocalDate()), userId);
    }
}
