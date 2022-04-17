package webshop.order;

import webshop.product.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Order {

    private Long id;
    private Long userId;
    private LocalDate date;

    public Order(Long id, Long userId, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
