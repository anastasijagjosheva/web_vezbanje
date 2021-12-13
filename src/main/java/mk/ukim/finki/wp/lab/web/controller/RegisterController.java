package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if( error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register( @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeatPassword,
                            @RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam LocalDate dateOfBirth){

        try {
            this.authService.register(username, password, repeatPassword, name, surname, dateOfBirth);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException | IllegalArgumentException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

