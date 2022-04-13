package webshop.product;

public class Product {

    private final long ID;
    private final String name;
    private final int price;
    private final int stock;

    public Product(long ID, String name, int price, int stock) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, int price, int stock) {
        this(0, name, price, stock);
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}
