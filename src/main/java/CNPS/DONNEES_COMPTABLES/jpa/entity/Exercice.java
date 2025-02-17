package CNPS.DONNEES_COMPTABLES.jpa.entity
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private UUID id_exercice;

    @Column (name ="lib_exercice")
    private String labelExercice;

    @Column(name = "date_start")
    private String dateStart;

    @Column(name = "date_end")
    private String dateEnd;
    
    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    private Users users

    @ManyToOne
    private Accounting_plan accounting_plan

    @ManyToOne
    private Company company;
}