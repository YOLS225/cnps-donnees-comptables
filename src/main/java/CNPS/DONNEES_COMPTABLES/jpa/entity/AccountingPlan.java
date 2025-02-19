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
@Table(name = "accounting_plan")
public class AccountingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_accounting_plan", nullable = false)
    private UUID id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

}