package webshop.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import webshop.product.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public long saveUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //userID,ID,email,password
        jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO users(name,email,password) VALUES(?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmailAddress());
            stmt.setInt(3, user.getPassword());
            return stmt;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public User findUserById(long id) {
        return (jdbcTemplate.queryForObject("select * from users where user_id = ?",
                (rs, rowNum) -> new User(rs.getInt("user_id"), rs.getString("name"),
                        rs.getString("email"), (rs.getInt("password")))
                , id));
    }
}
