package webshop.product;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import webshop.user.User;
import webshop.user.UserDao;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    Flyway flyway;
    MariaDbDataSource dataSource = new MariaDbDataSource();
    ProductDao productDao = new ProductDao(dataSource);
    String testTrueProductName = "TEST_ProductName";
    int testTrueInt = 123_456_789;

    @BeforeEach
    void init() {
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
    }

    @Test
    @DisplayName("TEST-Create: Location-object is successfully. ")
    void testCreate() {

        Product product = new Product(testTrueProductName, testTrueInt);

        assertEquals("Budapest", product.getName());
        assertEquals(123_456_789, product.getPrice());
    }
}