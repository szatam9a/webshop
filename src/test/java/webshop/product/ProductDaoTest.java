package webshop.product;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    ProductDao productDao;

    String testTrueProductName = "TEST_ProductName";
    int testTruePrice = 123_456_789;
    Product testProduct = new Product(testTrueProductName, testTruePrice);

    @BeforeEach
    void setUp() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        productDao = new ProductDao(dataSource);

        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
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
        List<Product> testProducts = productDao.listProducts();

        assertEquals(1, testProducts.size());
        assertEquals(0, testProducts.get(0).getID());
        assertEquals(testTrueProductName, testProducts.get(0).getName());
        assertEquals(testTruePrice, testProducts.get(0).getPrice());
        System.out.println(testProducts);
    }

      @Test
    @DisplayName("TEST-ID: Products ID is beautiful.")
    void testFindProductById() {
        productDao.insertProduct(testProduct);
        Product testProductActual = productDao.findProductById(1);

        assertEquals(false, testProductActual.equals(testProduct));
        assertEquals(1, testProductActual.getID());
        assertEquals(testTrueProductName, testProductActual.getName());
        assertEquals(testTruePrice, testProductActual.getPrice());
    }
}