package CNPS.DONNEES_COMPTABLES.jpa.entity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.enums.Status;
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
@Table(name = "exercice")
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_exercice", nullable = false )
    private UUID idExercice;

    @Column (name ="lib_exercice")
    private String labelExercice;

    @Column(name = "date_start")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;

    @Column(name = "date_end")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;


    @Column(name = "is_default")
    private Boolean isDefault;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Users users;

    @ManyToOne
    private AccountingPlan accountingPlan;

    @ManyToOne
    private Company company;
}