package webshop.product;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void saleProduct(long id, int amount) {
        Product product = productDao.findProductById(id);
        if(product.getStock() < amount) {
            throw new IllegalArgumentException("Low stock");
        }
        productDao.decreasedProductStock(id, amount);
    }
}
