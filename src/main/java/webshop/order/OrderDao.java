package webshop.order;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;

public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    public OrderDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertOrder(int userId, LocalDate date) {
        Date dateOrder = Date.valueOf(date);
        jdbcTemplate.update("Insert into orders(user_id, order_date) values (?,?)",userId, dateOrder);
    }

    public void insertOrderedItems(int orderId, int productId) {
        jdbcTemplate.update("Insert into ordered_items(order_id, product_id) values (?,?)", orderId, productId);
    }

    public Order findOrderByUserId(int userId) {
        return jdbcTemplate.queryForObject("Select * from orders where user_id = ?", (rs, rowNum)
                -> new Order(rs.getLong("id"), rs.getLong("user_id"), rs.getDate("order_date").toLocalDate()), userId);
    }
}
