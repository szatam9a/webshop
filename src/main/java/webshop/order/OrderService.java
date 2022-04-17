package webshop.order;

import webshop.user.User;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void saveOrder(User user) {
        orderDao.saveOrder(user);
    }
}
