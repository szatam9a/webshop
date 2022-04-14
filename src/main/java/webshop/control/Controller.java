package webshop.control;

import webshop.order.OrderService;
import webshop.product.ProductService;
import webshop.user.User;
import webshop.user.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public boolean isTerminated() {
        return terminated;
    }

    private boolean terminated;
    private User user;
    private ProductService productService;
    private UserService userService;
    private OrderService orderService;

    public Controller(ProductService productService, UserService userService, OrderService orderService) {
        this.user = user = new User("", "", 0);
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    public void printMenu() {

        System.out.println("\n- Fagyöngytyúk WEBSHOP -\n");

        if (user.isLoggedIn()) {
            System.out.println(user);
        }
        System.out.println("Kérem a kívánt funkcióhoz tartozó számot, és egy entert:");

        List<String> menuWebshop = Arrays.asList(
                "1. Felhasználó regisztráció",
                "2. Felhasználó bejelentkezés",
                "3. Termék kosárba helyezése",
                "4. Termék kosárból kivétele",
                "5. Termék mennyiség növelése",
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
        Scanner scan = new Scanner(System.in);
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
                User userToLogin = new User(scan.nextLine(), scan.nextLine(), scan.nextLine().hashCode());
                saveUser(userToLogin);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.print("\n Kérem a növelendő mennyiséget: ");
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                System.out.println("Kérem a feltöltendő lista-fájl abszolút elérési útvonalát: ");
                productService.loadProductFromFile(Path.of(scan.nextLine()));
                System.out.println("Feltöltve...");
                break;
            case 9:
                System.out.println("A Viszontlátásra :-)");
                terminated = true;
                break;
            default:
                System.out.println("Itt nincs ilyen: " + selection + " menüszám, kérem próbálkozzon újra. ");
                scan.nextLine();
                break;
        }
    }

    private void saveUser(User userToLogin) {
        if (userService.saveUser(userToLogin.getName(), userToLogin.getEmailAddress(), userToLogin.getPassword())) {
            user = new User(userService.findUserByEmail(userToLogin.getEmailAddress()));
            user.setLoggedIn(true);
        }
    }

    private void loginUser(User user) {
        if (userService.loginUser(user)) {
            this.user = new User(userService.findUserByEmail(user.getEmailAddress()));
            this.user.setLoggedIn(true);
        }
    }
}
