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
@Table(name = "account_class")
public class AccountClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_account_class", nullable = false)
    private UUID idAccountClass;

    @Column(name = "reference")
    private String reference;

    @Column(name = "label")
    private String label;

    @Column(name = "parent")
    private String parent;
}
