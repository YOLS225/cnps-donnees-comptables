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
@Table(name = "account_mistakes")
public class AccountMistakes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_mistakes_account", nullable = false )
    private UUID id;

    @Column (name ="label_account")
    private String labelAccount;

    @Column(name = "cell_number")
    private String cellNumber;

    @ManyToOne
    private RowBalance rowBalance;
}
