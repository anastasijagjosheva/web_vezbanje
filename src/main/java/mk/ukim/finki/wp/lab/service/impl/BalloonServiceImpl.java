package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBalloonRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryOrderRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepository;
//import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp.lab.repository.jpa.OrderRepository;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final InMemoryBalloonRepository balloonRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(InMemoryBalloonRepository balloonRepository, InMemoryManufacturerRepository manufacturerRepository, InMemoryOrderRepository orderRepository) {

        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;

    }
    public List<Balloon> listAll() {

        return balloonRepository.findAllBalloons();
    }

    /*@Override
    public List<Balloon> searchByNameOrDescription(String name, String description) {
        return balloonRepository.findAllByNameOrDescription(name,description);
    }*/

    @Override
    public List<Balloon> findAll() {

        return balloonRepository.findAll();
    }

    @Override

    public Optional<Balloon> save(String name, String description, Long manufacturerId) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        return this.balloonRepository.save(name, description, manufacturer);
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
