package mk.ukim.finki.wp.lab.model.exceptions;

public class OrderAlreadyInShoppingCartException extends RuntimeException{

    public OrderAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Order with id: %d already exists in shopping cart for user with username %s", id, username));
    }
}
