package webshop.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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


    public long saveUser(String name, String email, String password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //userID,ID,email,password
        jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO products(name,email,password) VALUES(?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);


            return stmt;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    // public boolean logIn (String name, String password) {
//}
}
