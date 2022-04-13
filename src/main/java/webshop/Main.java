package webshop;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import webshop.product.ProductDao;
import webshop.user.User;
import webshop.user.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scan = new Scanner(System.in);
    private boolean terminated;
    private UserDao userDao;
    private final ProductDao productDao = null;

    public static void main(String[] args) {

        MariaDbDataSource dataSource = new MariaDbDataSource();
        Main mainController = new Main();


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

        try {
            while (!mainController.terminated)
                mainController.runMenu();
        } catch (IOException ioe) {
            System.out.println("-= Exeption =- : " + ioe);
        }
    }

    public void printMenu() {
        System.out.println("\n- Műanyag üveggyöngy WEBSHOP -\nKérem a kívánt funkcióhoz tartozó számot, és egy entert:");
        List<String> menuWebshop = Arrays.asList(
                "1. Felhasználó regisztráció",
                "2. Felhasználó bejelentkezés",
                "3. Termék kosárba helyezése",
                "4. Termék kosárból kivétele",
                "5. Termékkészlet mennyiség növelése",
                "6. Rendelés leadása",
                "7. Termékek listázása fájlból",
                "8. Termékek betöltése fájlból",
                "9. Kilépés \n"
        );
        for (String item : menuWebshop) {
            System.out.println(item);
        }
    }

    public void runMenu() throws IOException {
        printMenu();

        int selection;
        try {
            selection = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException nfe) {
            selection = 0;
        }

        switch (selection) {
            case 1:
                System.out.print("\n Kérem a regisztrálni kívánt nevet, e-mail címet, jelszót: ");
                userDao.saveUser(new User(scan.nextLine(), scan.nextLine(), scan.nextLine().hashCode()));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.print("\n Kérem a bevételezni kívánt termék ID-jét, és bevételezendő mennyiséget.");
                productDao.increasedProductStock(scan.nextLong(),scan.nextInt());
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                System.out.println("Viszontlátásra");
                terminated = true;
                break;
            default:
                System.out.println("Nincs ilyen menüszám, kérem próbálkozzon újra.");
                scan.nextLine();
                break;
        }
    }

}
