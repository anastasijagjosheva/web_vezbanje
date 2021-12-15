package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Order;
import java.util.List;
import java.util.Optional;

public interface BalloonService {

        //List<Balloon> listAll();

        //List<Balloon> searchByNameOrDescription(String text);

        //List<Order> findAllOrders();

        List<Balloon> findAll(); //balloons1

        Optional<Balloon> save(String name, String description, Long manufacturerId);

        void deleteId(Long id);

        Optional<Balloon> findById(Long balloonId);

}
