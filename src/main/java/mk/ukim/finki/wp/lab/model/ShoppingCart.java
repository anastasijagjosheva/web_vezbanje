package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @ManyToMany
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {

        //this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(User user) {
        //this.id = (long) (Math.random() * 1000);
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.orders = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

}
