package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {


    public List<Balloon> findAllBalloons() {

        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloons1.stream()
                .filter(r -> r.getName().contains(text) || r.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    /*public List<Order> findAllOrders() {
        return DataHolder.orders;
    }*/

    public List<Balloon> findAll() {

        return DataHolder.balloons1;
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {

        DataHolder.balloons1.removeIf(i -> i.getName().equals(name));

        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloons1.add(balloon);

        return Optional.of(balloon);
    }

    public void deleteById(Long id){

        DataHolder.balloons1.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long balloonId){

        return DataHolder.balloons1.stream()
                .filter(r -> r.getId().equals(balloonId))
                .findFirst();
    }
}

