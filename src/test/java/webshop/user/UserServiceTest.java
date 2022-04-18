package webshop.user;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    Flyway flyway;
    UserDao userDao;
    UserService userService;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        userDao = new UserDao(dataSource);
        userService = new UserService(userDao);
    }

    @Test
    void findUserByIDTest() {

    }

    @Test
    void saveUserTest() {
        userService.saveUser("joe", "joe", "joe".hashCode());
        userService.saveUser("joe", "joee", "joe".hashCode());
        userService.saveUser("joe", "joeee", "joe".hashCode());
        userService.saveUser("joe", "joeeee", "joee".hashCode());
    }

    @Test
    void loginUserTestSuccess() {
        userService.saveUser("name", "email", "pass".hashCode());
        assertEquals(true, userService.loginUser(new User("name", "email", "pass".hashCode())));
    }

    @Test
    void loginUserTestFail() {
        userService.saveUser("joe", "joe", "joe".hashCode());
        assertEquals(false, userService.loginUser(new User("name", "email", "pas".hashCode())));
        assertEquals(false, userService.loginUser(new User("name", "emai", "pass".hashCode())));
    }
    @Test
    void registeringTheUserSuccess(){
        assertEquals(true, userService.saveUser("joe", "joee", "joe".hashCode()));
    }
    @Test
    void registeringTheUserFail(){
        userService.saveUser("joe", "joe", "joe".hashCode());
        assertEquals(false, userService.saveUser("joe", "joe", "joe".hashCode()));
    }
}