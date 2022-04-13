//package webshop.product;
//
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mariadb.jdbc.MariaDbDataSource;
//import webshop.user.User;
//import webshop.user.UserDao;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProductDaoTest {
//
//    Flyway flyway;
//    ProductDao productDao;
//
//    @BeforeEach
//    void init() {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        try {
//            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
//            dataSource.setUser("root");
//            dataSource.setPassword("root");
//        } catch (SQLException sqle) {
//            throw new IllegalStateException("Cannot reach DataBase!", sqle);
//        }
//
//        flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.clean();
//        flyway.migrate();
//
//        productDao = new ProductDao(dataSource);
//    }
//
//    @Test
//    void saveUserTest() {
//        long id = productDao.saveUser(new User("Name", "Email", "Password".hashCode()));
//        System.out.println(productDao.findUserById(id));
//    }
//
//}