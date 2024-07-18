package blueprint.springproject.models.entities;

import blueprint.springproject.models.dtos.Login;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/*
    That is only a user class base for authentication
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @UuidGenerator
    private String id;

    private String username;

    private String password;

    public User(Login login) {
        this.username = login.username();
        this.password = login.password();
    }
}
