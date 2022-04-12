package webshop.user;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


}
