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
    @Column(name="id_coherence", nullable = false )
    private UUID id_coherence;

    @Column (name ="label_coherence")
    private String labelCoherence;

    @Column(name = "account_coherence")
    private String account_coherence;


    @ManyToOne
    private Row_balance row_balance;
}