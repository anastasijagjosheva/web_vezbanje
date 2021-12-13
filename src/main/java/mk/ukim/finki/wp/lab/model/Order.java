package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String balloonColor;

    private String balloonSize;

    //private String clientName;
   //private String clientAddress;


    public Order() {
        this.Id = (long) (Math.random() * 1000);
    }

    public Order(String balloonColor, String balloonSize) {
        this.Id = (long) (Math.random() * 1000);
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        //this.clientName = clientName;
        //this.clientAddress = clientAddress;
    }

}
