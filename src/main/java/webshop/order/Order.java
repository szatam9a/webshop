package webshop.order;

import java.time.LocalDate;

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
