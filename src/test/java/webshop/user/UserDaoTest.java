package webshop.user;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    Flyway flyway;
    UserDao userDao;

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
    }

    @Test
    void saveUserTest() {
        long id = userDao.saveUser(new User("Name", "Email", "Password".hashCode()));
        assertEquals(id,userDao.findUserById(id).get().getID());
    }

    @Test
    void saveUserTest2() {
        long id = userDao.saveUser(new User("Name", "Email", "Password".hashCode()));
        assertEquals(false, userDao.findUserById(3).isPresent());
    }
}