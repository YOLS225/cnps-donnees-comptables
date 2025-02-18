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
@Table(name = "row_balance")
public class RowBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_row_balance", nullable = false )
    private UUID id_row_balance;

    @Column (name ="label_row_balance")
    private String labelRowBalance;

    @Column(name = "opening_sold")
    private String openingSold;

    @Column(name = "debit")
    private String debit;
    
    @Column(name = "credit")
    private String credit;

    @Column(name = "ending_sold")
    private String endingSold;

    @Column(name = "year_balance")
    private String yearBalance;

    @ManyToOne
    private Exercice exercice;
}