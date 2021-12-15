package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;

import mk.ukim.finki.wp.lab.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBalloonRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryOrderRepository;


import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepositoryJpa;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepositoryJpa balloonRepository;
    private final ManufacturerRepositoryJpa manufacturerRepository;


    public BalloonServiceImpl(BalloonRepositoryJpa balloonRepository, ManufacturerRepositoryJpa manufacturerRepository) {

        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;

    }
    /*public List<Balloon> listAll() {

        return balloonRepository.findAllBalloons();
    }/*

    /*@Override
    public List<Balloon> searchByNameOrDescription(String name, String description) {
        return balloonRepository.findAllByNameOrDescription(name,description);
    }*/

    @Override
    public List<Balloon> findAll() {

        return balloonRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.balloonRepository.deleteByName(name);

        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer)));
    }


    @Override
    public void deleteId(Long id) {

        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long balloonId) {

        return balloonRepository.findById(balloonId);
    }

    /*public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }*/

}
