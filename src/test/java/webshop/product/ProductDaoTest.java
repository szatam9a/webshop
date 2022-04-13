package webshop.product;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    Flyway flyway;
    MariaDbDataSource dataSource = new MariaDbDataSource();
    ProductDao productDao = new ProductDao(dataSource);

    String testTrueProductName = "TEST_ProductName";
    int testTruePrice = 123_456_789;
    Product testProduct = new Product(testTrueProductName, testTruePrice);

    @BeforeEach
    void init() {
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root555");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
    }

    @Test
    @DisplayName("TEST-Create: Product object created successfully.")
    void testCreate() {
        assertEquals("TEST_ProductName", testProduct.getName());
        assertEquals(123_456_789, testProduct.getPrice());
    }


    @Test
    @DisplayName("TEST-Insert: Insert Product is successfully.")
    void testInsertProduct() {
        productDao.insertProduct(testProduct);
        assertEquals(1, productDao.listProducts().size());
        assertEquals(0, productDao.listProducts().get(0).getID());
        assertEquals(testTrueProductName, productDao.listProducts().get(0).getName());
        assertEquals(testTruePrice, productDao.listProducts().get(0).getPrice());
    }

    @Test
    @DisplayName("TEST-List: Products List is beautiful.")
    void listProducts() {
        productDao.insertProduct(testProduct);
        assertEquals(1, productDao.listProducts().size());
        assertEquals(0, productDao.listProducts().get(0).getID());
        assertEquals(Arrays.asList(testProduct).toString(), productDao.listProducts().toString());
    }

    @Test
    @DisplayName("TEST-ID: Products ID is beautiful.")
    void findProductById() {

    }
}