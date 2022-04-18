package webshop;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import webshop.control.Controller;
import webshop.order.OrderDao;
import webshop.order.OrderService;
import webshop.product.ProductDao;
import webshop.product.ProductService;
import webshop.user.UserDao;
import webshop.user.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();

        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (
                SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        Controller mainController = new Controller(
                new ProductService(new ProductDao(dataSource)),
                new UserService(new UserDao(dataSource)),
                new OrderService(new OrderDao(dataSource)));

        try {
            while (!mainController.isTerminated())
                mainController.runMenu();
        } catch (
                IOException ioe) {
            System.out.println("-= Exeption =- : " + ioe);
        }
    }

}
