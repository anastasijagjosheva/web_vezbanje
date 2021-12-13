package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryOrderRepository {

    public Optional<Order> findById(Long id) {

        return DataHolder.orders.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public List<Order> findAll() {

        return DataHolder.orders;

    }
}
