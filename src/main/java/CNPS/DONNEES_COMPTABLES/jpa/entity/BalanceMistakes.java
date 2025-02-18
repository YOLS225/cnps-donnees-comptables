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
@Table(name = "balance_mistakes")
public class BalanceMistakes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_mistake_balance", nullable = false )
    private UUID idBalanceMistakes;

    @Column (name ="label_mistake_balance")
    private String labelMistakeBalance;

    @Column(name = "result_mistake_balance")
    private String resultMistakeAccount;

    @ManyToOne
    private RowBalance rowBalance;
}