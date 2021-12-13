package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryOrderRepository;

import mk.ukim.finki.wp.lab.repository.jpa.OrderRepository;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final InMemoryOrderRepository orderRepository;

    public OrderServiceImpl(InMemoryOrderRepository orderRepository) {

        this.orderRepository= orderRepository;
    }

    public List<Order> findAll() {

        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {

        return this.orderRepository.findById(orderId);
    }
}
