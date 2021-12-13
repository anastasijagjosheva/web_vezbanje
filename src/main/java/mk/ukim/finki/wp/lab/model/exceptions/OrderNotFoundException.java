package mk.ukim.finki.wp.lab.model.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super(String.format("Order with id %d was not found", id));
    }
}
