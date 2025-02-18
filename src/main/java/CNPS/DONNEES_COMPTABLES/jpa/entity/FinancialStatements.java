package CNPS.DONNEES_COMPTABLES.jpa.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "financial_statements")
public class FinancialStatements {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_financial_statements", nullable = false )
    private UUID id_financial_statements;

    @Column (name ="lib_financial_statements")
    private String labelExercice;

    @Column(name = "creation_date_finanacial_statements")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDateFinancialStatements;

    @Column(name = "status_financial_statements")
    private String statusFinancialStatetments;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Exercice exercice;
}