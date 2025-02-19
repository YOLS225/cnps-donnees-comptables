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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_account", nullable = false)
    private UUID id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

//    @Column(name = "parent_account")
//    private String parentAccount;

    @ManyToOne
    private Status status;

    @ManyToOne
    private AccountingPlan AccountingPlan;

    @ManyToOne
    private AccountClass accountingClass;
}