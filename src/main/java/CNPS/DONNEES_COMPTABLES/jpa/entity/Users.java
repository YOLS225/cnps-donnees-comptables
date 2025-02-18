package CNPS.DONNEES_COMPTABLES.jpa.entity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;
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
    private UUID idUsers;

    @Column (name ="last_name_users")
    private String lastNameUsers;

    @Column(name = "first_name_users")
    private String firstNameUsers;

    @Column(name = "email_users")
    private String emailUsers;

    @Column(name = "last_connexion_users")
    private String lastConnexionUsers;

    @ManyToOne
    private Status status;

    @Column(name = "status_users")
    private String statusUsers;

    @ManyToOne
    private Company company;
}