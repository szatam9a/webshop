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
}
