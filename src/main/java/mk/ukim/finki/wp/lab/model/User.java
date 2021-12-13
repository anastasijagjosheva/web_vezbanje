package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "balloon_users")
public class User {

    //private Long id;
    @Id
    private String username;

    private String name;

    private String surname;

    private String password;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User() {
    }

    public User(String username, String name, String surname, String password, LocalDate dateOfBirth) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;

    }

}
