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
public class FinancialStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_financial_statements", nullable = false )
    private UUID id;

    @Column (name ="label")
    private String label;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDateFinancialStatements;

    @ManyToOne
    private Status status;

    @ManyToOne
    private User users;

    @ManyToOne
    private Exercice exercice;
}