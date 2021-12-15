package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryManufacturerRepository;
//import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepositoryJpa;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepositoryJpa manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepositoryJpa manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long manufacturerId) {
        return this.manufacturerRepository.findById(manufacturerId);
    }

    @Override
    public Optional<Manufacturer> save(Long id, String name, String country, String address) {

        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, country, address)));
    }

    @Override
    public void deleteById(Long id) {

        this.manufacturerRepository.deleteById(id);
    }
}
