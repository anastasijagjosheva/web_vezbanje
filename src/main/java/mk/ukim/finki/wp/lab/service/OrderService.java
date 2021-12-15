package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    //Order placeOrder(String balloonColor, String clientName, String address);
    Optional<Order> findOrderById(Long orderId);
    List<Order> findAll();
    Optional<Order> save(String balloonColor, String balloonSize);
}
