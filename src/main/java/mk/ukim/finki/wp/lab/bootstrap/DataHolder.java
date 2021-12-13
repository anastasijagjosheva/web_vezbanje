package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Balloon> balloons1 = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    @PostConstruct
    void init(){

        Manufacturer manufacturer1 = new Manufacturer("nike", "Makedonija", "Skopje-Centar");
        Manufacturer manufacturer2 = new Manufacturer("adidas", "Makedonija", "Skopje-Karposh");
        Manufacturer manufacturer3 = new Manufacturer("puma", "Makedonija", "Skopje-Aerodrom");
        Manufacturer manufacturer4 = new Manufacturer("New balance", "Makedonija", "Skopje-Vodno");
        Manufacturer manufacturer5 = new Manufacturer("Timberlake", "Makedonija", "Skopje-Kapistec");


        balloons.add(new Balloon("Red", "Balloon1", manufacturer1));
        balloons.add(new Balloon("Green", "Balloon2", manufacturer2));
        balloons.add(new Balloon("Blue", "Balloon3", manufacturer3));
        balloons.add(new Balloon("Purple", "Balloon4", manufacturer4));
        balloons.add(new Balloon("Yellow", "Balloon5", manufacturer5));
        balloons.add(new Balloon("Orange", "Balloon6", manufacturer1));
        balloons.add(new Balloon("Pink", "Balloon7", manufacturer2));
        balloons.add(new Balloon("White", "Balloon8", manufacturer3));
        balloons.add(new Balloon("Black", "Balloon9", manufacturer4));
        balloons.add(new Balloon("Brown", "Balloon10", manufacturer5));

        balloons1.add(new Balloon("Red", "Balloon1", manufacturer1));
        balloons1.add(new Balloon("Green", "Balloon2", manufacturer2));


        manufacturers.add(manufacturer1);
        manufacturers.add(manufacturer2);
        manufacturers.add(manufacturer3);
        manufacturers.add(manufacturer4);
        manufacturers.add(manufacturer5);

        users.add(new User("anastasija.gjosheva", "Anastasija", "Gjosheva", "agj", LocalDate.of(2000,8,23)));

    }
}
