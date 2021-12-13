package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp.lab.model.exceptions.OrderAlreadyInShoppingCartException;
import mk.ukim.finki.wp.lab.model.exceptions.OrderNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryOrderRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryShoppingCartRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryUserRepository;

import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final InMemoryOrderRepository orderRepository;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository  shoppingCartRepository, InMemoryUserRepository  userRepository, InMemoryOrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);

        return this.shoppingCartRepository.findById(cartId).get().getOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addOrderToShoppingCart(String username, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        if(shoppingCart.getOrders().stream()
                .filter(i -> i.getId().equals(orderId))
                .collect(Collectors.toList()).size() > 0)
            throw new OrderAlreadyInShoppingCartException(orderId, username);

        shoppingCart.getOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}
