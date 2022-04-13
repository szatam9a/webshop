package webshop.product;

public class Product {

    private final long id;
    private final String name;
    private final int price;
//    private final int stock;

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
//        this.stock = stock;
    }

    public Product(String name, int price) {
        this(0, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

//    public int getStock() {
//        return stock;
//    }

}
