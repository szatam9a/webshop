package webshop.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

//    public void saleProduct(long id, int amount) {
//        Product product = productDao.findProductById(id);
//        if (product.getStock() < amount) {
//            throw new IllegalArgumentException("Low stock");
//        }
//        productDao.increasedProductStock(id, amount);
//    }

    public void loadProductFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.readLine();
            while (br.ready()) {
                addProductFromLine(br.readLine());
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot open file for read!", e);
        }
    }

    private void addProductFromLine(String line) {
        String[] elements = line.split(";");
        productDao.insertProduct(
                new Product(elements[0], Integer.parseInt(elements[1]))
        );
    }

    public void listProducts() {
        productDao.listProducts();
    }
}
