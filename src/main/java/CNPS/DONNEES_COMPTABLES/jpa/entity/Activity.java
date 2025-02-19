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
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_activity", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @ManyToOne
    private Company company;
}
