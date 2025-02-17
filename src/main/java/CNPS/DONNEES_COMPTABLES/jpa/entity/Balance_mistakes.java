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
@Table(name = "balance_mistakes")
public class Balance_mistakes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_mistake_balance", nullable = false )
    private UUID id_mistake_balance;

    @Column (name ="label_mistake_balance")
    private String labelMistakeBalance;

    @Column(name = "result_mistake_balance")
    private String resultMistakeAccount;


    @ManyToOne
    private Row_balance row_balance;
}