package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {


    public List<Manufacturer> findAll(){

        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long manufacturerId){

        return DataHolder.manufacturers.stream()
                .filter(r -> r.getId().equals(manufacturerId))
                .findFirst();
    }


    public Optional<Manufacturer> save(Long id, String name, String country, String address) {

        DataHolder.manufacturers.removeIf(i -> i.getId().equals(id));
        Manufacturer manufacturer = new Manufacturer(name, country, address);
        DataHolder.manufacturers.add(manufacturer);

        return Optional.of(manufacturer);
    }


    public void deleteById(Long id) {
        DataHolder.manufacturers.removeIf(i -> i.getId().equals(id));

    }
}
