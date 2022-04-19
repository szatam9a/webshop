package webshop;

//import webshop.product.ProductDao;


public class Ancient_Main {

//    private final Scanner scan = new Scanner(System.in);
//    private boolean terminated;
//    private static UserDao userDao;
//    private static OrderDao orderDao;
//    private static ProductDao productDao;
//    private static ProductService productService;
//    //private final ProductDao productDao = null;
//
//
////    public Main(UserDao userDao) {
////        this.userDao = userDao;
////    }
//
//    public static void main(String[] args) {
//
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        Main mainController = new Main();
//
//        try {
//            dataSource.setUrl("jdbc:mariadb://localhost:3306/webshop?useUnicode=true");
//            dataSource.setUser("webshops");
//            dataSource.setPassword("webshops");
//        } catch (
//                SQLException sqle) {
//            throw new IllegalStateException("Cannot reach DataBase!", sqle);
//        }
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.clean();
//        flyway.migrate();
//
//        orderDao = new OrderDao(dataSource);
//        orderDao.insertOrder(11, LocalDate.of(2022, 02, 11));
//
//        try {
//            while (!mainController.terminated)
//                mainController.runMenu();
//        } catch (IOException ioe) {
//            System.out.println("-= Exeption =- : " + ioe);
//        }
//    }
//
//    public void printMenu() {
//        System.out.println("\n- Fa gyöngytyúk WEBSHOP -\nKérem a kívánt funkcióhoz tartozó számot, és egy entert:");
//        List<String> menuWebshop = Arrays.asList(
//                "1. Felhasználó regisztráció",
//                "2. Felhasználó bejelentkezés",
//                "3. Termék kosárba helyezése",
//                "4. Termék kosárból kivétele",
//                "5. Termék mennyiség növelése",
//                "6. Rendelés leadása",
//                "7. Termékek listázása fájlból",
//                "8. Termékek betöltése fájlból",
//                "9. Kilépés \n"
//        );
//        for (String item : menuWebshop) {
//            System.out.println(item);
//        }
//    }
//
//    public void runMenu() throws IOException {
//        printMenu();
//
//        int selection;
//        try {
//            selection = Integer.parseInt(scan.nextLine());
//        } catch (NumberFormatException nfe) {
//            selection = 0;
//        }
//
//        switch (selection) {
//            case 1:
//                System.out.print("\n Kérem a regisztrálni kívánt nevet, e-mail címet, jelszót: ");
//                userDao.saveUser(new User(scan.nextLine(), scan.nextLine(), scan.nextLine().hashCode()));
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                System.out.print("\n Kérem a növelendő mennyiséget: ");
//                break;
//            case 6:
//                break;
//            case 7:
//                System.out.println("Termékek: " +productDao.listProducts());
//                break;
//            case 8:
//                System.out.println("Kérem a feltöltendő lista-fájl abszolút elérési útvonalát: ");
//                productService.loadProductFromFile(Path.of(scan.nextLine()));
//                System.out.println("Feltöltve...");
//                break;
//            case 9:
//                System.out.println("A Viszontlátásra :-)");
//                terminated = true;
//                break;
//            default:
//                System.out.println("Itt nincs ilyen: " +selection+ " menüszám, kérem próbálkozzon újra. ");
//                scan.nextLine();
//                break;
//        }
//    }
}
