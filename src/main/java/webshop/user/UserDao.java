package webshop.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

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
            stmt.setLong(3, user.getPassword());
            return stmt;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<User> findUserById(long id) {
        try {
            return Optional.of((jdbcTemplate.queryForObject("select * from users where user_id = ?",
                    (rs, rowNum) -> new User(rs.getInt("user_id"), rs.getString("name"),
                            rs.getString("email"), (rs.getLong("password")))
                    , id)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> findUserByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("select * from users where name = ?",
                    (rs, rowNum) -> new User(rs.getInt("user_id"), rs.getString("name"),
                            rs.getString("email"), (rs.getLong("password")))
                    , name));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> findUserByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("select * from users where email = ?",
                    (rs, rowNum) -> new User(rs.getInt("user_id"), rs.getString("name"),
                            rs.getString("email"), (rs.getLong("password")))
                    , email));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void UpdateUser(User user) {
        jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE user set name = ? where id = ? ",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            return stmt;
        });
    }
}
