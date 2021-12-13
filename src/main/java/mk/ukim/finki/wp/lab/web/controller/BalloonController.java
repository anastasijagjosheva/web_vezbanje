package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){

        /*if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }*/

        List<Balloon> balloons = this.balloonService.listAll();
        List<Balloon> balloons1 = this.balloonService.findAll();
        model.addAttribute("balloons", balloons);
        model.addAttribute("balloons1", balloons1);

        return "listBalloons";
    }


    @PostMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteId(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {

        if (this.balloonService.findById(id).isPresent()) {

            Balloon balloon = this.balloonService.findById(id).get();

            List<Manufacturer> manufacturers = this.manufacturerService.findAll();

            model.addAttribute("balloon", balloon);

            model.addAttribute("manufacturers", manufacturers);

            return "add-balloon";
        }
        return "redirect:/balloons?error=ProductNotFound";
    }

    @PostMapping("/add-form")
    public String getAddBalloonPage(Model model) {

        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        return "add-balloon";
    }


    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturerId){

        this.balloonService.save(name, description, manufacturerId);
        return "redirect:/balloons";

    }

}
