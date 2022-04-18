package webshop.product;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductServiceIT {

    ProductDao productDao;
    ProductService productService;

    String testTrueProductName = "TEST_ProductName";
    int testTruePrice = 123_456_789;
    Product testProduct = new Product(testTrueProductName, testTruePrice);

    @BeforeEach
    void setUp() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        productDao = new ProductDao(dataSource);
        productService = new ProductService(productDao);

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
    @DisplayName("TEST-LoadFile: Products loadid from file successfully.")
    void testLoadProductFromFile() {

        assertEquals(0, productDao.listProducts().size());

        productService.loadProductFromFile(Path.of("src/test/resources/TEST_Products.csv"));
        List<Product> testProductsActual = productDao.listProducts();

        assertEquals(4, testProductsActual.size());
        assertEquals("Gumikutya", testProductsActual.get(0).getName());
        assertEquals(100, testProductsActual.get(0).getPrice());
        assertEquals(0, testProductsActual.get(0).getID());
    }

    @Test
    @DisplayName("TEST-WrongLoadFile: Products wrong loadid from file successfully. :-)")
    void testWrongLoadProductFromFile() {
        IllegalArgumentException iae =
                assertThrows(IllegalArgumentException.class,
                        () -> productService.loadProductFromFile(
                                Path.of("THE HITCHHIKER'S GUIDE TO THE GALAXY")));
        assertEquals("Cannot open file for read!", iae.getMessage());
    }

    @Test
    @DisplayName("TEST-List: Products List is beautiful.")
    void testListProducts() {
        productDao.insertProduct(testProduct);
        List<Product> testProducts = productDao.listProducts();

        assertEquals(1, testProducts.size());
        assertEquals(0, testProducts.get(0).getID());
        assertEquals(List.of(testProduct).toString(), testProducts.toString());
    }
}