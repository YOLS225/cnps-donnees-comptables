package CNPS.DONNEES_COMPTABLES.jpa.entity;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_users", nullable = false )
    private UUID id;

    @Column (name ="lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Company company;
}