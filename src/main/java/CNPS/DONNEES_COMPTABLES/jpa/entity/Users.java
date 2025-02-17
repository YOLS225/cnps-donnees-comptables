package CNPS.DONNEES_COMPTABLES.jpa.entity
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_users", nullable = false )
    private UUID id_users;

    @Column (name ="last_name_users")
    private String lastNameUsers;

    @Column(name = "first_name_users")
    private String firstNameUsers;

    @Column(name = "email_users")
    private String emailUsers;

    @Column(name = "last_connexion_users")
    private String lastConnexionUsers;

    @Column(name = "status_users")
    private String statusUsers;

    @ManyToOne
    private Company company;
}