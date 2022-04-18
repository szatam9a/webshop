package webshop.control;

import webshop.order.OrderService;
import webshop.product.Product;
import webshop.product.ProductService;
import webshop.user.User;
import webshop.user.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Controller {

    public boolean isTerminated() {
        return terminated;
    }

    private boolean terminated;
    private Scanner scan = new Scanner(System.in);
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

    public void initProductTable() {
        productService.loadProductFromFile(Path.of("src/test/resources/TEST_Products.csv"));
    }

    public void printMenu() {

        System.out.println("\n-= Fagyöngytyúk WEBSHOP =-\n");

        if (user.isLoggedIn()) {
            System.out.println(user);
        }
        System.out.println("Kezit csókolom, Idösanyám aztat kérdezteti, hogy gyött-é tokaszalonna," +
                "\nha akad némi ideje kérem a kívánt funkcióhoz tartozó számot, és egy entert:");

        List<String> menuWebshop = new LinkedList<>(Arrays.asList(
                "1. Felhasználó regisztráció",
                "2. Felhasználó bejelentkezés",
                "3. Termék kosárba helyezése",
                "4. Termék kosárból kivétele",
                "5. Termék mennyiség növelése",
                "6. Rendelés leadása",
                "7. Termékek listázása",
                "9. Kilépés \n"
        ));
        if (user.isLoggedIn()) {
            menuWebshop.remove(0);
            menuWebshop.remove(0);
        }
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
        User userToLogin;
        switch (selection) {
            case 1:
                System.out.print("\n Kérem a regisztrálni kívánt nevet, e-mail címet, jelszót: ");
                String name = scan.nextLine();
                String email = scan.nextLine();
                int password = scan.nextLine().hashCode();
                userToLogin = new User(name, email, password);
                saveUser(userToLogin);
                break;
            case 2:
                System.out.print("\n Kérem az email címed: ");
                String emailt = scan.nextLine();
                System.out.print("\n Kérem a jelszót: ");
                String passwordt = scan.nextLine();
                userToLogin = new User(emailt, passwordt);
                loginUser(userToLogin);
                break;
            case 3:
                productService.listProducts().stream().forEach(System.out::println);
                System.out.println("\n Kérem az termék id-ját");
                String prodID = scan.nextLine();
                addToCart(prodID);
                break;
            case 4:
                showProducts();
                System.out.println("\n Kérem az termék id-ját");
                String prodToRemove = scan.nextLine();
                removeFromCart(prodToRemove);
                break;
            case 5:
                System.out.print("\n Kérem a növelendő mennyiséget: ");
                break;
            case 6:
                checkThenOrder();
                break;
            case 7:
                productService.listProducts().stream().forEach(System.out::println);
                break;
            case 8:
                System.out.println("Kérem a feltöltendő lista-fájl (abszolút) elérési útvonalát: ");
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

    private void removeFromCart(String prodToRemove) {
        user.getShoppingCart().remove(new Product(Integer.parseInt(prodToRemove), "", 0));
    }

    private void checkThenOrder() {
        if (user.isLoggedIn()) {
            orderService.saveOrder(user);
            System.out.println("rendelés leadva ty.");
            user.setShoppingCart(new LinkedHashMap<>());
        } else {
            System.out.println("you need to login to order");
        }
    }

    private void addToCart(String prodID) {
        try {
            Product toAdd = productService.findProductById(Integer.parseInt(prodID));
            if (toAdd != null) user.addToCart(toAdd);
        } catch (Exception e) {
            System.out.println("id kell");
        }

    }

    private void showProducts() {
        for (Map.Entry<Product, Integer> entry : user.getShoppingCart().entrySet()) {
            System.out.println(entry);
        }
    }

    private void saveUser(User userToLogin) {
        if (userService.saveUser(userToLogin.getName(), userToLogin.getEmailAddress(), userToLogin.getPassword())) {
            user.copyUser(userService.findUserByEmail(userToLogin.getEmailAddress()));
            user.setLoggedIn(true);
        }
    }

    private void loginUser(User user) {
        if (userService.loginUser(user)) {
            this.user.copyUser(userService.findUserByEmail(user.getEmailAddress()));
            this.user.setLoggedIn(true);
            System.out.println("User be van lépve");
        } else {
            this.user.setLoggedIn(false);
            System.out.println("User nincs belépve");
        }
    }
}
