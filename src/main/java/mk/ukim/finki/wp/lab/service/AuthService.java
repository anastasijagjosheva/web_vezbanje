package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDate;
import java.util.Date;

public interface AuthService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, String dateOfBirth);
}

