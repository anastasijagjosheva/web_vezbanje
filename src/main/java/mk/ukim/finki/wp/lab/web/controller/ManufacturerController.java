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
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturerPage(@RequestParam(required = false) String error, Model model) {

        /*if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }*/

        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        return "listManufacturers";
    }

    @PostMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        this.manufacturerService.deleteId(id);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditManufacturerPage(@PathVariable Long id, Model model) {

        if (this.manufacturerService.findById(id).isPresent()) {
            Manufacturer manufacturer = this.manufacturerService.findById(id).get();
            model.addAttribute("manufacturer", manufacturer );

            return "add-manufacturer";
        }
        return "redirect:/manufacturers";
    }

    @PostMapping("/add-form")
    public String getAddManufacturerPage(Model model) {

        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        return "add-manufacturer";
    }


    @PostMapping("/add")
    public String saveManufacturer(@RequestParam String name,
                              @RequestParam String country,
                              @RequestParam String address,
                              @RequestParam Long id) {

        this.manufacturerService.save(id,name, country, address);
        return "redirect:/manufacturers";

    }

}
