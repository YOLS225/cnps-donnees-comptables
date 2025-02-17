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
@Table(name = "coherence_mistakes")
public class Coherence_mistakes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_mistake_account", nullable = false )
    private UUID id_mistake_account;

    @Column (name ="label_mistake_account")
    private String labelMistakeAccount;

    @Column(name = "num_cell_mistake_account")
    private String numCellMistakeAccount;


    @ManyToOne
    private Row_balance row_balance;
}